package com.zrytech.framework.app.mapper;

import java.util.List;

import com.zrytech.framework.app.dto.car.CarPageDto;
import com.zrytech.framework.app.entity.Car;

public interface CarMapper {
	
    int updateByPrimaryKeySelective(Car record);

    /**
     * 车辆搜索
     * @author cat
     * 
     * @param record
     * @return
     */
    List<Car> selectSelective(CarPageDto record); 
    
}