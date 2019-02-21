package com.zry.framework.mapper;

import java.util.List;

import com.zry.framework.dto.CarCargoOwnnerPageDto;
import com.zry.framework.entity.CarCargoOwnner;

public interface CarCargoOwnerMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(CarCargoOwnner record);

    int insertSelective(CarCargoOwnner record);

    CarCargoOwnner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarCargoOwnner record);

    int updateByPrimaryKey(CarCargoOwnner record);
    
    List<CarCargoOwnner> selectSelective(CarCargoOwnnerPageDto dto);
    
}