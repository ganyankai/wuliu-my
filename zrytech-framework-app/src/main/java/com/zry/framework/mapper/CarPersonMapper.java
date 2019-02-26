package com.zry.framework.mapper;

import java.util.List;

import com.zry.framework.dto.CarPersonPageDto;
import com.zry.framework.dto.carperson.CarOwnerCarPersonPageDto;
import com.zry.framework.entity.CarPerson;

public interface CarPersonMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(CarPerson record);

    int insertSelective(CarPerson record);

    CarPerson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarPerson record);

    int updateByPrimaryKey(CarPerson record);
    
    List<CarPerson> selectSelective(CarPersonPageDto dto);
    
    List<CarPerson> carOwnerCarPersonPage(CarOwnerCarPersonPageDto dto);
}