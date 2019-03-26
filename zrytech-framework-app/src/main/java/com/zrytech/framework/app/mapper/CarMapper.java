package com.zrytech.framework.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zrytech.framework.app.dto.car.CarOwnerCarPageDto;
import com.zrytech.framework.app.dto.car.CarPageDto;
import com.zrytech.framework.app.entity.Car;

public interface CarMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);
    
    
    /**
     * 管理员搜索车辆信息
     * @author cat
     * 
     * @param record
     * @return
     */
    List<Car> selectSelective(CarPageDto record); 
    
    /**
     * 车主查询车辆分页
     * @author cat
     * 
     * @param record
     * @return
     */
    List<Car> carOwnerSelectSelective(@Param("record") CarOwnerCarPageDto record, @Param("carOwnerId") Integer carOwnerId); 
}