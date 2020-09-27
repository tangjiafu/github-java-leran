package com.debug.kill.server.service;

import com.debug.kill.model.entity.RedDetail;
import com.debug.kill.model.entity.RedRecord;
import com.debug.kill.model.entity.RedRobRecord;
import com.debug.kill.model.mapper.RedDetailMapper;
import com.debug.kill.model.mapper.RedRecordMapper;
import com.debug.kill.model.mapper.RedRobRecordMapper;
import com.debug.kill.server.entity.RedPacketDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@EnableAsync
public class RedServiceImpl implements IRedService {

	/**
	 * 发红包，红包全局唯一id
	 */
	@Resource
	private RedRecordMapper redRecordMapper;
	/**
	 * 发红包，详细红包金额
	 */
	@Resource
	private RedDetailMapper redDetailMapper;
	/**
	 * 抢红包
	 */
	@Resource
	private RedRobRecordMapper redRobRecordMapper;

	/**
	 * @param dto       数据传输
	 * @param redId     红包id
	 * @param redPacket 红包详细金额
	 */
	@Override
	@Async
	@Transactional(rollbackFor = Exception.class)
	public void recordRedPacket(RedPacketDto dto, String redId, List<Integer> redPacket){
		// 定义红包实体对象
		RedRecord redRecord = new RedRecord();
		redRecord.setUserId(dto.getUserId());
		redRecord.setRedPacket(redId);
		redRecord.setTotal(dto.getNum());
		redRecord.setAmount(BigDecimal.valueOf(dto.getTotalMoney()));
		redRecord.setCreateTime(new Date());
		redRecordMapper.insertSelective(redRecord);
		// 定义红包随机金额明细实体类对象
		RedDetail detail;
		// 遍历随机金额列表，将金额等信息设置到相应字段中
		for(Integer i : redPacket){
			detail = new RedDetail();
			detail.setRecordId(redRecord.getId());
			detail.setAmount(BigDecimal.valueOf(i));
			detail.setCreateTime(new Date());
			redDetailMapper.insertSelective(detail);
		}
	}

	/**
	 * 抢红包记录
	 *
	 * @param userId 用户id
	 * @param redId  红包id
	 * @param money  红包金额
	 */
	@Override
	public void recordRobRedPacket(Integer userId, String redId, BigDecimal money) throws Exception{
		// 抢到红包记录
		RedRobRecord redRobRecord = new RedRobRecord();
		redRobRecord.setUserId(userId);
		redRobRecord.setRedPacket(redId);
		redRobRecord.setAmount(money);
		redRobRecord.setRobTime(new Date());
		redRobRecordMapper.insertSelective(redRobRecord);
	}
}
