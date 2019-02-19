package com.zry.framework.mapper;

import java.util.List;

import com.zry.framework.dto.CarPageDto;
import com.zry.framework.entity.Car;

public interface CarMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);
    
    List<Car> selectSelective(CarPageDto record); 
}