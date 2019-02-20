package com.zry.framework.mapper;

import java.util.List;

import com.zry.framework.dto.CargoMatterPageDto;
import com.zry.framework.entity.CargoMatter;

/**
 * 报价单
 * 
 * @author cat
 *
 */
public interface CargoMatterMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(CargoMatter record);

    int insertSelective(CargoMatter record);

    CargoMatter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CargoMatter record);

    int updateByPrimaryKey(CargoMatter record);
    
    List<CargoMatter> selectSelective(CargoMatterPageDto dto);
    
}