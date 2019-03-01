package com.zrytech.framework.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zrytech.framework.app.dto.CargoMatterPageDto;
import com.zrytech.framework.app.dto.cargomatter.CarOwnerCargoMatterPageDto;
import com.zrytech.framework.app.entity.CargoMatter;

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
    
    /**
     * 车主搜索报价单
     * @param dto
     * @return
     */
    List<CargoMatter> carOwnerSelectSelective(@Param("dto") CarOwnerCargoMatterPageDto dto, @Param("carOwnerId") Integer carOwnerId);
    
}