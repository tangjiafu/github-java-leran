package com.debug.kill.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 红包明细
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedDetail {
	private Integer id;
	private Integer recordId;  //红包记录id
	private BigDecimal amount; //红包明细金额
	private Byte isActive; //是否有效
	private Date createTime;
}
