package com.zrytech.framework.app.mapper;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dto.WaybillPageDto;
import com.zrytech.framework.app.entity.Waybill;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;

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

    int updateIndentStatus(Waybill waybill);

    PageInfo<Waybill> indentPage(@Param("waybill") Waybill waybill, Page page);

    List<String> coundIndent(@Param("cargoOwnnerId") Integer cargoOwnnerId);

    Waybill get(@Param("id") Integer id);

    int changeIndent(Waybill waybill);

    int delete(@Param("id") Integer id);
}