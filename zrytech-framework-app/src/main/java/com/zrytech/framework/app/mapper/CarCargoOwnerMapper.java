package com.zrytech.framework.app.mapper;

import java.util.List;

import com.zrytech.framework.app.dto.CarCargoOwnnerPageDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;

public interface CarCargoOwnerMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(CarCargoOwnner record);

    int insertSelective(CarCargoOwnner record);

    CarCargoOwnner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarCargoOwnner record);

    int updateByPrimaryKey(CarCargoOwnner record);
    
    List<CarCargoOwnner> selectSelective(CarCargoOwnnerPageDto dto);

    //随机挑选5条车主
    List<Integer> randSelectCarOwner();
}