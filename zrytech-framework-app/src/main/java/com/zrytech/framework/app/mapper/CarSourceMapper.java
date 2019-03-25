package com.zrytech.framework.app.mapper;

import java.util.List;

import com.zrytech.framework.app.dto.carsource.CarSourcePageDto;
import com.zrytech.framework.app.entity.CarSource;

/**
 * 车源
 * 
 * @author cat
 *
 */
public interface CarSourceMapper {

	List<CarSource> selectSelective(CarSourcePageDto dto);

}