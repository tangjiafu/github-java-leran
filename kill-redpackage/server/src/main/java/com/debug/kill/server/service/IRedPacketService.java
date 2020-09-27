package com.debug.kill.server.service;

import com.debug.kill.server.entity.RedPacketDto;

import java.math.BigDecimal;

public interface IRedPacketService {
	/**
	 * 发红包
	 *
	 * @param dto 红包数据
	 * @return String 红包id
	 * @throws Exception
	 */
	String handOut(RedPacketDto dto) throws Exception;

	/**
	 * 抢红包
	 *
	 * @param userId 用户id
	 * @param redId  红包id
	 * @return BigDecimal 抢到红包金额
	 * @throws Exception
	 */
	BigDecimal rob(Integer userId, String redId) throws Exception;
}
