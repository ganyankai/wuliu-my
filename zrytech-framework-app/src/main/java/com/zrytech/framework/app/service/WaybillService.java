package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.DeleteDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.WaybillDto;
import com.zrytech.framework.base.entity.Page;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.WaybillPageDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillDetailAddDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface WaybillService {
	
	public ServerResponse page(WaybillPageDto dto, Integer pageNum, Integer pageSize);
	
	public ServerResponse details(Integer id);

    /**
     * @return
     * @Desinition:创建运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    ServerResponse createIndent(WaybillDto waybillDto);

    /**
     * @return
     * @Desinition:待确认运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    ServerResponse confirmIndent(WaybillDto waybillDto);

    /**
     * @return
     * @Desinition:运单分页列表展示
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    ServerResponse indentPage(WaybillDto waybillDto, Page page);

    /**
     * @return
     * @Desinition:运单统计
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    ServerResponse coundIndent(WaybillDto waybillDto);

    /**
     * @return
     * @Desinition:运单详情
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    ServerResponse get(WaybillDto waybillDto);

    /**
     * @return
     * @Desinition:更改运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    ServerResponse changeIndent(WaybillDto waybillDto);

    /**
     * @return
     * @Desinition:删除运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    ServerResponse delete(WaybillDto waybillDto);
    
    public ServerResponse details(DetailsDto dto, Customer customer);
    
    public ServerResponse page(WaybillPageDto dto, Integer pageNum, Integer pageSize, Customer customer);
    
    public ServerResponse deleteWaybillDetail(DeleteDto dto, Customer customer);
    
    public ServerResponse deleteBillLocation(DeleteDto dto, Customer customer);
    
    public ServerResponse addWaybillDetail(WaybillDetailAddDto dto, Customer customer);
    
    
}
