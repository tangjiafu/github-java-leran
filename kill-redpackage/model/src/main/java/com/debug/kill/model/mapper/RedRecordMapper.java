package com.debug.kill.model.mapper;

import com.debug.kill.model.entity.RedRecord;

public interface RedRecordMapper {
	// 根据主键id删除
	int deleteByPrimaryKey(Integer id);

	// 插入数据记录
	int insert(RedRecord redRecord);

	// 条件数据记录
	int insertSelective(RedRecord redRecord);

	// 依据主键id查询记录
	RedRecord selectByPrimaryKey(Integer id);

	// 条件更新
	int updateByPrimaryKeySelective(RedRecord redRecord);

	//依据主键更新
	int updateByPrimaryKey(RedRecord redRecord);
}
