package com.zrytech.framework.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zrytech.framework.dto.CarPageDto;
import com.zrytech.framework.dto.car.CarOwnerCarPageDto;
import com.zrytech.framework.entity.Car;

public interface CarMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);
    
    List<Car> selectSelective(CarPageDto record); 
    
    /**
     * 车主查询车辆分页
     * @param record
     * @return
     */
    List<Car> carOwnerCarPage(@Param("record") CarOwnerCarPageDto record, @Param("carOwnerId") Integer carOwnerId); 
}