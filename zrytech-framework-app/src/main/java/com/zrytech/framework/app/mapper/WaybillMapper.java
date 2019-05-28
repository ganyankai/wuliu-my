package com.zrytech.framework.app.mapper;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.dto.waybill.CarOwnerWaybillPageDto;
import com.zrytech.framework.app.dto.waybill.WaybillPageDto;
import com.zrytech.framework.app.entity.Waybill;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 运单
 * 
 * @author cat
 *
 */
public interface WaybillMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Waybill record);

    int insertSelective(Waybill record);

    Waybill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Waybill record);

    int updateByPrimaryKey(Waybill record);
    
    List<Waybill> selectSelective(WaybillPageDto dto);
    
    List<Waybill> carOwnerSelectSelective(@Param("dto") CarOwnerWaybillPageDto dto, @Param("carOwnerId") Integer carOwnerId);

    int updateIndentStatus(Waybill waybill);

    PageInfo<Waybill> indentPage(@Param("waybill") Waybill waybill, Page page);

    List<String> coundIndent(@Param("cargoOwnnerId") Integer cargoOwnnerId);

    Waybill get(@Param("id") Integer id);

    int changeIndent(Waybill waybill);

    int delete(@Param("id") Integer id);
    
	@Update("update `waybill` set `status` = #{status} where `id` = #{id}")
	int updateStatusById(@Param("id") Integer id, @Param("status") String status);
    
	/**
	 * 车主提交运单，将运单状态从待生成改为待确认
	 * @author cat
	 * 
	 * @param id
	 * @return
	 */
	@Update("update `waybill` set `status` = '" + CargoConstant.WAYBILL_STATUS_WAIT_DETERMINE + "' where status = '"
			+ CargoConstant.WAYBILL_STATUS_WAIT_GENERATE + "' and `id` = #{id}")
	int submit(@Param("id") Integer id);
    
}