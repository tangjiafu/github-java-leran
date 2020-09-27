package com.debug.kill.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 红包记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedRecord {
	private Integer id;
	private Integer userId; //用户id
	private String redPacket; // 红包全局唯一标识
	private Integer total; //可以抢的人数
	private BigDecimal amount; //红包总金额
	private Byte isActive; //是否有效
	private Date createTime;
}
