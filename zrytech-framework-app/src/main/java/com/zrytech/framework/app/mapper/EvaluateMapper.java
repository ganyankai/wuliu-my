package com.zrytech.framework.app.mapper;

import com.zrytech.framework.app.entity.Evaluate;

public interface EvaluateMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    Evaluate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);
}