package com.debug.kill.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 抢到的红包
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class RedRobRecord {
	private Integer id;
	private Integer userId; //用户主键
	private String redPacket; //红包全局唯一标识
	private BigDecimal amount; // 抢到的红包金额
	private Date robTime; //抢红包时间
	private Byte isActive; //是否有效
}
