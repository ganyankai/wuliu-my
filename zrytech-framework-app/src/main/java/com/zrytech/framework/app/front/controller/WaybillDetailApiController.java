package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.app.ano.CarOwnerRole;
import com.zrytech.framework.app.ano.CargoOwnerRole;
import com.zrytech.framework.app.ano.NeedCertified;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.billlocation.BillLocationAddDto;
import com.zrytech.framework.app.dto.waybill.WaybillPageDto;
import com.zrytech.framework.app.dto.waybill.WaybillUpdateDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillCommonDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillDetailAddDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillDetailUpdateDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.Waybill;
import com.zrytech.framework.app.entity.WaybillDetail;
import com.zrytech.framework.app.service.BillLocationService;
import com.zrytech.framework.app.service.WaybillDetailService;
import com.zrytech.framework.app.service.WaybillService;
import com.zrytech.framework.app.service.impl.CargoServiceImpl;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 运单项
 */
@RestController
@RequestMapping("/api/waybilldetail")
public class WaybillDetailApiController {

	@Autowired
	private WaybillDetailService service;


	/**
	 * 查询运输中运单项详情
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/list")
	public ServerResponse cargoOwnerWaybillPage(@RequestBody @Valid RequestParams<WaybillCommonDto> requestParams,
			BindingResult result) {
		Page page = requestParams.getPage();
		if (page == null) {
			page = new Page(1, 10);
		}
		Integer pageNum = page.getPageNum();
		Integer pageSize = page.getPageSize();
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		Customer customer = RequestUtil.getCurrentUser(Customer.class);

		ServerResponse response = null;
		if(customer.getCustomerType().equalsIgnoreCase("car_owner")){
			Integer carId = customer.getCarOwner().getId();
			//车主
			response =  service.getCarWbdList(pageNum,pageSize,carId);
		}else if(customer.getCustomerType().equalsIgnoreCase("cargo_owner")){
			//货主
			Integer cargoId = customer.getCarOwner().getId();
			response =  service.getCargoWbdList(pageNum,pageSize,cargoId);
		}
		return ServerResponse.successWithData(response);
	}



}
