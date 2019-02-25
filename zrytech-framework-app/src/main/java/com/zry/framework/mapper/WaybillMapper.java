package com.zry.framework.mapper;

import java.util.List;

import com.zry.framework.dto.WaybillPageDto;
import com.zry.framework.entity.Waybill;

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

    int createIndent(Waybill waybill);
}