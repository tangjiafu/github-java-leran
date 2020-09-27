package com.debug.kill.server.service;

import com.debug.kill.server.entity.RedPacketDto;
import com.debug.kill.server.util.RedPackageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedPacketServiceImpl implements IRedPacketService {

	@Autowired
	private IRedService redService;
	@Autowired
	private RedisTemplate redisTemplate;
	/**
	 * redis缓存key前缀
	 */
	public static final String keyPrefix = "redis:red:packet:";

	/**
	 * 发红包
	 *
	 * @param dto 红包数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public String handOut(RedPacketDto dto) throws Exception{
		if(dto.getTotalMoney() <= 0){
			throw new Exception("红包金额不能小于等于0");
		}
		if(dto.getNum() <= 0){
			throw new Exception("红包人数不能小于等于0");
		}
		// 二倍均值生成红包
		List<Integer> redlist = RedPackageUtil.divideRedPackage(dto.getTotalMoney(), dto.getNum());
		// 生成红包唯一id
		String timestamp = String.valueOf(System.nanoTime());
		String redId = new StringBuffer(keyPrefix).append(dto.getUserId()).append(":").append(timestamp).toString();
		//将红包金额存入缓存中
		redisTemplate.opsForList().leftPushAll(redId, redlist);
		//红包数量缓存到redis
		String redTotalKey = redId + ":total";
		redisTemplate.opsForValue().set(redTotalKey, dto.getNum());
		//将发红包信息，每个红包明细存入数据库
		redService.recordRedPacket(dto, redId, redlist);
		//返回红包唯一标识串
		return redId;
	}

	/**
	 * 抢红包
	 *
	 * @param userId 用户id
	 * @param redId  红包id
	 * @return 红包金额
	 * @throws Exception
	 */
	@Override
	public BigDecimal rob(Integer userId, String redId) throws Exception{
		ValueOperations valueOperations = redisTemplate.opsForValue();
		Object isRobed = valueOperations.get(redId + ":" + userId + ":rob");
		// 判断该用户是否抢过红包，如果抢过红包，直接返回红包金额
		if(isRobed != null){
			return new BigDecimal(isRobed.toString());
		}
		//查看是否还有红包
		Boolean res = click(redId);
		// 红包没有了
		if(!res){
			return null;
		}
		//******start******************对用户加分布式锁*********************************//
		final String lockKey = redId + ":" + userId + "-lock";
		Boolean lock = valueOperations.setIfAbsent(lockKey, redId, 24L, TimeUnit.HOURS);
		//******************************      分布式锁********************************//
		try {
			if(lock){
				Object value = redisTemplate.opsForList().rightPop(redId);
				// 没有抢到红包
				if(value == null){
					return null;
				}
				//抢到红包，更新缓存红包数量
				String redNumKey = redId + ":total";
				Long increment = valueOperations.decrement(redNumKey);
				//计算抢红包金额
				BigDecimal result = new BigDecimal(value.toString()).divide(new BigDecimal(100));
				//抢红包记录插入数据库,使用value没有用result,保证数据库单位统一
				redService.recordRobRedPacket(userId, redId, new BigDecimal(value.toString()));
				//缓存当前用户已经抢过红包
				valueOperations.set(redId + ":" + userId + ":rob", result, 24L, TimeUnit.HOURS);
				log.info("用户抢到红包：userId={} redId={} 金额={}元", userId, redId, result.toString());
				return result;
			}
		} catch (Exception e) {
			throw new Exception("系统异常-抢红包-加分布式锁异常");
		} finally {
			//*************************释放分布式锁********************************//
			redisTemplate.delete(lockKey);
		}
		return null;
	}

	/**
	 * 点红包业务逻辑，返回false，表示没有红包
	 *
	 * @param redId 红包唯一标识符
	 * @return
	 * @throws Exception
	 */
	private Boolean click(String redId) throws Exception{
		ValueOperations valueOperations = redisTemplate.opsForValue();
		//取剩下红包数量,redis是单线程;
		String redNumKey = redId + ":total";
		Object total = valueOperations.get(redNumKey);
		if(total != null && Integer.valueOf(total.toString()) > 0){return true;}
		return false;

	}
}
