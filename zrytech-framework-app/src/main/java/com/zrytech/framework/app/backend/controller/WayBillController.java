package com.zrytech.framework.app.backend.controller;

import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.WaybillPageDto;
import com.zrytech.framework.app.entity.Waybill;
import com.zrytech.framework.app.service.WaybillService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 运单
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("admin/waybill")
public class WayBillController {

    @Autowired
    private WaybillService waybillService;
    

    
    /**
     * 管理员 - 某一个车主的运单分页
     * @author cat
     * 
     * @param requestParams
     * @param result
     * @return
     */
    @Valid
    @PostMapping("/oneCarOwnerWaybillPage")
	public ServerResponse oneCarOwnerWaybillPage(@RequestBody @Valid RequestParams<WaybillPageDto> requestParams,
			BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		WaybillPageDto dto = requestParams.getParams();
		if (dto.getCarOwnerId() == null) {
			throw new BusinessException(112, "车主不能为空");
		}
		PageData<Waybill> pageData = waybillService.waybillPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
    
    
    /**
     * 管理员 - 某一个货主的运单分页
     * @author cat
     * 
     * @param requestParams
     * @param result
     * @return
     */
	@Valid
	@PostMapping("/oneCargoOwnerWaybillPage")
	public ServerResponse oneCargoOwnerWaybillPage(@RequestBody @Valid RequestParams<WaybillPageDto> requestParams,
			BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		WaybillPageDto dto = requestParams.getParams();
		if (dto.getCargoOwnerId() == null) {
			throw new BusinessException(112, "货主不能为空");
		}
		PageData<Waybill> pageData = waybillService.waybillPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
    
    
	/**
	 * 管理员 - 运单分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<WaybillPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		PageData<Waybill> pageData = waybillService.waybillPage(requestParams.getParams(), pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 运单详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/details")
	public ServerResponse details(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result) {
		return waybillService.adminDetails(requestParams.getParams());
	}
	
}
