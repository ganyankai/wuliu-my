package com.zrytech.framework.app.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.ano.CarOwnerRole;
import com.zrytech.framework.app.ano.CargoOwnerRole;
import com.zrytech.framework.app.constants.CustomerConstants;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerUpdateDto;
import com.zrytech.framework.app.dto.carcargoowner.OrganizeInfoUpdateDto;
import com.zrytech.framework.app.dto.carcargoowner.PersonInfoUpdateDto;
import com.zrytech.framework.app.dto.customer.CustomerRegisterDto;
import com.zrytech.framework.app.dto.customer.TelDto;
import com.zrytech.framework.app.dto.customer.UserAccountDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.service.CarCargoOwnerService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.RequestUtil;


/**
 * 前台 - 车主货主
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/carCargoOwner")
public class CarCargoOwnerAPIController {

	@Autowired
	private CarCargoOwnerService carCargoOwnerService;
	
	
	@RequestMapping("/getCustomer")
	public ServerResponse getCustomer() {
		return carCargoOwnerService.getCustomer();
	}

	/**
	 * 验证手机号是否已注册
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/checkTel")
	public ServerResponse checkTel(@RequestBody @Valid RequestParams<TelDto> requestParams, BindingResult result) {
		try {
			carCargoOwnerService.checkTel(requestParams.getParams().getTel());
		} catch (Exception e) {
			return ServerResponse.successWithData(true);
		}
		return ServerResponse.successWithData(false);
	}

	
	/**
	 * 验证用户名是否已注册
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/checkUserAccount")
	public ServerResponse checkUserAccount(@RequestBody @Valid RequestParams<UserAccountDto> requestParams,
			BindingResult result) {
		try {
			carCargoOwnerService.checkUserAccount(requestParams.getParams().getUserAccount());
		} catch (Exception e) {
			return ServerResponse.successWithData(true);
		}
		return ServerResponse.successWithData(false);
	}
	
	/**
	 * 车主注册
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/carOwnerRegister")
	public ServerResponse carOwnerRegister(@RequestBody @Valid RequestParams<CustomerRegisterDto> requestParams,
			BindingResult result) {
		CustomerRegisterDto params = requestParams.getParams();
		params.setCustomerType(CustomerConstants.TYPE_CAR_OWNER);
		return carCargoOwnerService.register(params);
	}
	
	/**
	 * 货主注册
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/cargoOwnerRegister")
	public ServerResponse cargoOwnerRegister(@RequestBody @Valid RequestParams<CustomerRegisterDto> requestParams,
			BindingResult result) {
		CustomerRegisterDto params = requestParams.getParams();
		params.setCustomerType(CustomerConstants.TYPE_CARGO_OWNER);
		return carCargoOwnerService.register(params);
	}
	
	/**
	 * 个人货主修改认证信息
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@CargoOwnerRole
	@Valid
	@RequestMapping("/cargoOwnerUpdatePersonInfo")
	public ServerResponse cargoOwnerUpdatePersonInfo(
			@RequestBody @Valid RequestParams<PersonInfoUpdateDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carCargoOwnerService.cargoOwnerUpdatePersonInfo(requestParams.getParams(), customer);
	}

	/**
	 * 企业货主修改认证信息
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@CargoOwnerRole
	@Valid
	@RequestMapping("/cargoOwnerUpdateOrganizeInfo")
	public ServerResponse cargoOwnerUpdateOrganizeInfo(
			@RequestBody @Valid RequestParams<OrganizeInfoUpdateDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carCargoOwnerService.cargoOwnerUpdateOrganizeInfo(requestParams.getParams(), customer);
	}

	/**
	 * 个人车主修改认证信息
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@CarOwnerRole
	@Valid
	@RequestMapping("/carOwnerUpdatePersonInfo")
	public ServerResponse carOwnerUpdatePersonInfo(@RequestBody @Valid RequestParams<PersonInfoUpdateDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carCargoOwnerService.carOwnerUpdatePersonInfo(requestParams.getParams(), customer);
	}

	/**
	 * 企业车主修改认证信息
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@CarOwnerRole
	@Valid
	@RequestMapping("/carOwnerUpdateOrganizeInfo")
	public ServerResponse carOwnerUpdateOrganizeInfo(
			@RequestBody @Valid RequestParams<OrganizeInfoUpdateDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carCargoOwnerService.carOwnerUpdateOrganizeInfo(requestParams.getParams(), customer);
	}
	
	/**
	 * 修改车主不需要审批的字段
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@CarOwnerRole
	@Valid
	@RequestMapping("/updateCarOwner")
	public ServerResponse updateCarOwner(@RequestBody @Valid RequestParams<CarCargoOwnerUpdateDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carCargoOwnerService.updateCarOwner(requestParams.getParams(), customer);
	}
	
	/**
	 * 修改货主不需要审批的字段
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@CargoOwnerRole
	@Valid
	@RequestMapping("/updateCargoOwner")
	public ServerResponse updateCargoOwner(@RequestBody @Valid RequestParams<CarCargoOwnerUpdateDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carCargoOwnerService.updateCargoOwner(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车主详情
	 * @author cat
	 * 
	 * @return
	 */
	@CarOwnerRole
	@Valid
	@RequestMapping("/carOwnerDetails")
	public ServerResponse carOwnerDetails() {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carCargoOwnerService.carOwnerDetails(customer);
	}
	
	
	/**
	 * 货主详情
	 * @author cat
	 * 
	 * @return
	 */
	@CargoOwnerRole
	@Valid
	@RequestMapping("/cargoOwnerDetails")
	public ServerResponse cargoOwnerDetails() {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carCargoOwnerService.cargoOwnerDetails(customer);
	}
	
	
}
