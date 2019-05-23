package com.zrytech.framework.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.zrytech.framework.app.constants.CargoMatterConstants;
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
    
    
    /**
     * 将货源的应标中的报价单状态改为未中标，已中标的报价单除外
     * 
     * @param id	已中标的报价单Id
     * @param cargoId	货源Id
     * @return
     */
	@Update("update `cargo_matter` set `status` = '" + CargoMatterConstants.CARGO_MATTER_STATUS_UN_TENDER
			+ "' where `id` != #{id} and `cargo_id` = #{cargo_id} and status = '"
			+ CargoMatterConstants.CARGO_MATTER_STATUS_RELEASE + "'")
	int updateStatusByIdAndCargoId(@Param("id") Integer id, @Param("cargoId") Integer cargoId);
}