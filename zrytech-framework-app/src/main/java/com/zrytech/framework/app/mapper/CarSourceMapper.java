package com.zrytech.framework.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zrytech.framework.app.dto.carsource.CarOwnerCarSourcePageDto;
import com.zrytech.framework.app.dto.carsource.CarSourcePageDto;
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
    
    
    /**
     * 车主搜索车源
     * @param dto
     * @return
     */
    List<CarSource> carOwnerSelectSelective(CarOwnerCarSourcePageDto dto);
    
    
    /**
     * 车主 - 我的车源
     * @param dto	搜索条件
     * @param carOwnerId	车主Id
     * @return
     */
    List<CarSource> myCarSource(@Param("dto") CarOwnerCarSourcePageDto dto, @Param("carOwnerId") Integer carOwnerId);
}