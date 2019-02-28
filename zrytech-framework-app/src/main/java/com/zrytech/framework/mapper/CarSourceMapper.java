package com.zrytech.framework.mapper;

import java.util.List;

import com.zrytech.framework.dto.CarSourcePageDto;
import com.zrytech.framework.entity.CarSource;

/**
 * 车源
 * @author cat
 *
 */
public interface CarSourceMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(CarSource record);

    int insertSelective(CarSource record);

    CarSource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarSource record);

    int updateByPrimaryKey(CarSource record);
    
    List<CarSource> selectSelective(CarSourcePageDto dto);
    
}