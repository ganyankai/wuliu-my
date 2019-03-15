package com.zrytech.framework.app.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.dto.CarCargoOwnnerPageDto;
import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerUpdateAvoidAuditDto;
import com.zrytech.framework.app.service.CarCargoOwnerService;
import com.zrytech.framework.base.annotation.CurrentUser;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.User;

/**
 * 后台管理系统 - 车主货主
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/admin/carCargoOwner")
public class CarCargoOwnerController {

	@Autowired
	private CarCargoOwnerService carCargoOwnerService;

	
	/**
	 * 车主货主分页
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Deprecated
	@Valid
	@RequestMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CarCargoOwnnerPageDto> requestParams, BindingResult result,
			@CurrentUser User user) {
		return carCargoOwnerService.page(requestParams.getParams(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize());
	}
	
	
	/**
	 * 车主货主详情
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Deprecated
	@Valid
	@RequestMapping("/details")
	public ServerResponse details(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result,
			@CurrentUser User user) {
		return carCargoOwnerService.details(requestParams.getParams().getId());
	}

	
	/**
	 * 车辆审核
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Deprecated
	@Valid
	@RequestMapping("/check")
	public ServerResponse check(@RequestBody @Valid RequestParams<CheckDto> requestParams, BindingResult result,
			@CurrentUser User user) {
		return carCargoOwnerService.check(requestParams.getParams(), user);
	}
	
	
	
	
	/**
	 * 管理员 - 车主审批
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/approveCarOwner")
	public ServerResponse approveCarOwner(@RequestBody @Valid RequestParams<CheckDto> requestParams, BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carCargoOwnerService.approveCarOwner(requestParams.getParams(), user);
	}
	
	
	/**
	 * 管理员 - 货主审批
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/approveCargoOwner")
	public ServerResponse approveCargoOwner(@RequestBody @Valid RequestParams<CheckDto> requestParams, BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carCargoOwnerService.approveCargoOwner(requestParams.getParams(), user);
	}
	
	
	/**
	 * 管理员 - 车主分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/carOwnerPage")
	public ServerResponse carOwnerPage(@RequestBody @Valid RequestParams<CarCargoOwnnerPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		return carCargoOwnerService.carOwnerPage(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 管理员 - 货主分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/cargoOwnerPage")
	public ServerResponse cargoOwnerPage(@RequestBody @Valid RequestParams<CarCargoOwnnerPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		return carCargoOwnerService.cargoOwnerPage(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 管理员 - 车主详情
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/carOwnerDetails")
	public ServerResponse carOwnerDetails(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result) {
		return carCargoOwnerService.carOwnerDetails(requestParams.getParams());
	}
	
	
	/**
	 * 管理员 - 货主详情
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/cargoOwnerDetails")
	public ServerResponse cargoOwnerDetails(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result) {
		return carCargoOwnerService.cargoOwnerDetails(requestParams.getParams());
	}
	
	
	
	/**
	 * 管理员 - 修改车主免审核状态
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/carOwnerUpdateAvoidAudit")
	public ServerResponse carOwnerUpdateAvoidAudit(@RequestBody @Valid RequestParams<CarCargoOwnerUpdateAvoidAuditDto> requestParams, BindingResult result) {
		return carCargoOwnerService.carOwnerUpdateAvoidAudit(requestParams.getParams());
	}
	
	
	/**
	 * 管理员 - 修改货主免审核状态
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/cargoOwnerUpdateAvoidAudit")
	public ServerResponse cargoOwnerUpdateAvoidAudit(@RequestBody @Valid RequestParams<CarCargoOwnerUpdateAvoidAuditDto> requestParams, BindingResult result) {
		return carCargoOwnerService.cargoOwnerUpdateAvoidAudit(requestParams.getParams());
	}
	
	
}
