package com.zrytech.framework.app.mapper;

import java.util.List;

import com.zrytech.framework.app.dto.CarSourcePageDto;
import com.zrytech.framework.app.entity.CarSource;

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