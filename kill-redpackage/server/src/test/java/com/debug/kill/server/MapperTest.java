package com.debug.kill.server;

import com.debug.kill.model.entity.RedRecord;
import com.debug.kill.model.mapper.RedRecordMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
public class MapperTest {
	@Resource
	RedRecordMapper redRecordMapper;

	@Test
	@Async
	@Transactional(rollbackFor = Exception.class)
	void recordTest(){
		RedRecord redRecord = new RedRecord();
		redRecord.setUserId(100010);
		redRecord.setRedPacket("redis:red:packet:10010:77665782203963");
		redRecord.setTotal(10);
		redRecord.setAmount(new BigDecimal(1000));
		redRecord.setCreateTime(new Date());
		System.out.println(redRecord.toString());
		int i = redRecordMapper.insertSelective(redRecord);
		System.out.println(i);
		//		redRecordMapper.selectByPrimaryKey(1);

	}
}
