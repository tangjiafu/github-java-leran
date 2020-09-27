package com.debug.kill.server.service;

import com.debug.kill.server.entity.RedPacketDto;

import java.math.BigDecimal;
import java.util.List;

public interface IRedService {
	/**
	 * 发红包
	 *
	 * @param dto       数据传输
	 * @param redId     红包id
	 * @param redPacket 红包详细金额
	 */
	void recordRedPacket(RedPacketDto dto, String redId, List<Integer> redPacket);

	/**
	 * 抢红包
	 *
	 * @param userId 用户id
	 * @param redId  红包id
	 * @param money  红包金额
	 */
	void recordRobRedPacket(Integer userId, String redId, BigDecimal money) throws Exception;
}
