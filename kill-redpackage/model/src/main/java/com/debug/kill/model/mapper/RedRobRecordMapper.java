package com.debug.kill.model.mapper;

import com.debug.kill.model.entity.RedRecord;
import com.debug.kill.model.entity.RedRobRecord;

public interface RedRobRecordMapper {
	/**
	 * 依据主键删除
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入记录
	 */
	int insert(RedRecord redRecord);

	/**
	 * 条件插入
	 */
	int insertSelective(RedRobRecord record);

	/**
	 * 依据主键查询
	 */
	RedRobRecordMapper selectByPrimaryKey(Integer id);

	/**
	 * 条件更新
	 */
	int updateByPrimaryKeySelective(RedRobRecordMapper record);

	/**
	 * 依据主键更新
	 */
	int updateByPrimaryKey(RedRobRecordMapper record);
}
