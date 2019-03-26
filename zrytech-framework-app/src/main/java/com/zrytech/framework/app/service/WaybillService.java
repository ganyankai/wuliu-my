package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.DeleteDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.WaybillDto;
import com.zrytech.framework.app.entity.Waybill;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.PageData;

import com.zrytech.framework.app.dto.WaybillPageDto;
import com.zrytech.framework.app.dto.waybill.CarOwnerWaybillPageDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillDetailAddDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.entity.ServerResponse;


public interface WaybillService {
	
    /**
     * 运单分页
     * @author cat
     * 
     * @param dto	搜索条件
     * @param pageNum
     * @param pageSize
     * @return
     */
	public PageData<Waybill> waybillPage(WaybillPageDto dto, Integer pageNum, Integer pageSize);
	
	/**
     * 管理员 - 运单详情
     * @author cat
     * 
     * @param dto
     * @return
     */
    public ServerResponse adminDetails(DetailsDto dto);
    
    


	

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

    public ServerResponse page(CarOwnerWaybillPageDto dto, Integer pageNum, Integer pageSize, Customer customer);

    public ServerResponse deleteWaybillDetail(DeleteDto dto, Customer customer);

    public ServerResponse deleteBillLocation(DeleteDto dto, Customer customer);

    public ServerResponse addWaybillDetail(WaybillDetailAddDto dto, Customer customer);

    /**
     * @return
     * @Desinition:取消运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    ServerResponse cancelIndent(WaybillDto waybillDto);

    /**
     * @return
     * @Desinition:签收订单(已收货待支付状态)
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    ServerResponse signAccpet(WaybillDto waybillDto);
}
