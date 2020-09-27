package com.debug.kill.model.mapper;

import com.debug.kill.model.entity.RedDetail;

public interface RedDetailMapper {
	//依据主键查询
	RedDetail selectByPrimaryKey(Integer id);

	//依据主键删除
	int deleteByPrimaryKey(Integer id);

	//插入一条红包明细
	int insert(RedDetail redDetail);

	// 条件插入
	int insertSelective(RedDetail redDetail);


	//依据主键条件更新
	int updateByPrimaryKeySelecttive(RedDetail redDetail);

	//依据主键更新
	int updateByPrimaryKey(RedDetail redDetail);
}
