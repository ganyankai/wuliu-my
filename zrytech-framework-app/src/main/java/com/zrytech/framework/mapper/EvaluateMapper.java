package com.zrytech.framework.mapper;

import com.zrytech.framework.entity.Evaluate;

public interface EvaluateMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    Evaluate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);
}