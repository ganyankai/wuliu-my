package com.zry.framework.mapper;

import com.zry.framework.entity.CarCargoOwnner;

public interface CarCargoOwnerMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(CarCargoOwnner record);

    int insertSelective(CarCargoOwnner record);

    CarCargoOwnner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarCargoOwnner record);

    int updateByPrimaryKey(CarCargoOwnner record);
    
    
}