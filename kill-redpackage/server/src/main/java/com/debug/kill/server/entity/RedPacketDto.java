package com.debug.kill.server.entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RedPacketDto {

	/**
	 * 用户名
	 */
	private Integer userId;
	/**
	 * 红包数量
	 */
	@NotNull
	private Integer num;
	/**
	 * 红包总金额
	 */
	@NotNull
	private Integer totalMoney;
}
