package com.zrytech.framework.mapper;

import java.util.List;

import com.zrytech.framework.dto.CarCargoOwnnerPageDto;
import com.zrytech.framework.entity.CarCargoOwnner;

public interface CarCargoOwnerMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(CarCargoOwnner record);

    int insertSelective(CarCargoOwnner record);

    CarCargoOwnner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarCargoOwnner record);

    int updateByPrimaryKey(CarCargoOwnner record);
    
    List<CarCargoOwnner> selectSelective(CarCargoOwnnerPageDto dto);
    
}