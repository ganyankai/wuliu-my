package com.zrytech.framework.app.mapper;

import java.util.List;

import com.zrytech.framework.app.dto.CarPersonPageDto;
import com.zrytech.framework.app.dto.carperson.CarOwnerCarPersonPageDto;
import com.zrytech.framework.app.entity.CarPerson;

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