package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.app.ano.CarOwnerRole;
import com.zrytech.framework.app.ano.CargoOwnerRole;
import com.zrytech.framework.app.ano.NeedCertified;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.billlocation.BillLocationAddDto;
import com.zrytech.framework.app.dto.waybill.WaybillPageDto;
import com.zrytech.framework.app.dto.waybill.WaybillUpdateDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillDetailAddDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillDetailUpdateDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.Waybill;
import com.zrytech.framework.app.service.BillLocationService;
import com.zrytech.framework.app.service.WaybillService;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.RequestUtil;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运单
 */
@RestController
@RequestMapping("/api/waybill")
public class WaybillApiController {

	@Autowired
	private WaybillService service;

	@Autowired
	private BillLocationService billLocationService;

	@NeedCertified
	@CargoOwnerRole
	@Valid
	@PostMapping("/confirm")
	public ServerResponse confirm(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.confirm(requestParams.getParams());
	}

	@NeedCertified
	@CargoOwnerRole
	@Valid
	@PostMapping("/cargoOwnerWaybillPage")
	public ServerResponse cargoOwnerWaybillPage(@RequestBody @Valid RequestParams<WaybillPageDto> requestParams,
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
		WaybillPageDto dto = requestParams.getParams();
		dto.setCargoOwnerId(customer.getCargoOwner().getId());
		PageData<Waybill> pageData = service.waybillPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}

	@NeedCertified
	@CargoOwnerRole
	@Valid
	@PostMapping("/cargoOwnerDetails")
	public ServerResponse cargoOwnerDetails(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return service.cargoOwnerDetails(requestParams.getParams());
	}

	// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/addWaybillDetail")
	public ServerResponse addWaybillDetail(@RequestBody @Valid RequestParams<WaybillDetailAddDto> requestParams,
			BindingResult result) {
		return service.addWaybillDetail(requestParams.getParams());
	}

	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/updateWaybillDetail")
	public ServerResponse updateWaybillDetail(@RequestBody @Valid RequestParams<WaybillDetailUpdateDto> requestParams,
			BindingResult result) {
		return service.updateWaybillDetail(requestParams.getParams());
	}

	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/submit")
	public ServerResponse submit(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.submit(requestParams.getParams());
	}

	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/cancel")
	public ServerResponse cancel(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.cancel(requestParams.getParams());
	}

	/**
	 * 车主或车主子账号 - 新增或更新运单装卸地
	 * 
	 * <pre>
	 * 如果指定的运单装卸地已存在则更新，根据运单Id,运单项Id,货源装卸地Id确认运单装卸地
	 * </pre>
	 */
	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/addBillLocation")
	public ServerResponse addBillLocation(@RequestBody @Valid RequestParams<BillLocationAddDto> requestParams,
			BindingResult result) {
		return billLocationService.addBillLocation(requestParams.getParams());
	}

	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/deleteWaybillDetail")
	public ServerResponse deleteWaybillDetail(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return service.deleteWaybillDetail(requestParams.getParams());
	}

	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/deleteBillLocation")
	public ServerResponse deleteBillLocation(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return service.deleteBillLocation(requestParams.getParams());
	}

	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/update")
	public ServerResponse update(@RequestBody @Valid RequestParams<WaybillUpdateDto> requestParams,
			BindingResult result) {
		return service.update(requestParams.getParams());
	}

	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/carOwnerWaybillPage")
	public ServerResponse carOwnerWaybillPage(@RequestBody @Valid RequestParams<WaybillPageDto> requestParams,
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
		WaybillPageDto dto = requestParams.getParams();
		dto.setCarOwnerId(customer.getCarOwner().getId());
		PageData<Waybill> pageData = service.waybillPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}

	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/carOwnerDetails")
	public ServerResponse carOwnerDetails(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return service.carOwnerDetails(requestParams.getParams());
	}
	
	@NeedCertified
	@CargoOwnerRole
	@Valid
	@PostMapping("/confirmReceipt")
	public ServerResponse confirmReceipt(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return service.confirmReceipt(requestParams.getParams());
	}

}
