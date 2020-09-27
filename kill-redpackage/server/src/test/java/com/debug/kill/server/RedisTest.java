package com.debug.kill.server;

import com.debug.kill.server.entity.RedPacketDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

@SpringBootTest
public class RedisTest {
	@Autowired
	RedisTemplate redisTemplate;

	/**
	 * 批量删除key
	 */
	@Test
	void delPatternKey(){
		Set<String> keys = redisTemplate.keys("redis:red:packet:*");
		for(String key : keys){
			Boolean delete = redisTemplate.delete(key);
			System.out.println(key + " ==> delete: " + delete);
		}

	}

	@Test
	void putAndGet(){
		RedPacketDto redPacketDto = new RedPacketDto(1001, 10, 1000);
		redisTemplate.opsForValue().set(redPacketDto.toString(), redPacketDto);
		System.out.println(redisTemplate.opsForValue().get(redPacketDto.toString()));
	}
}
