package com.zrytech.framework.app.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.ano.AdminRole;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.CarCargoOwnerConstants;
import com.zrytech.framework.app.dto.CarCargoOwnnerPageDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerUpdateAvoidAuditDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.service.CarCargoOwnerService;
import com.zrytech.framework.base.entity.PageData;
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
	 * 管理员 - 车主分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/carOwnerPage")
	public ServerResponse carOwnerPage(@RequestBody @Valid RequestParams<CarCargoOwnnerPageDto> requestParams,
			BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CarCargoOwnnerPageDto dto = requestParams.getParams();
		dto.setType(CarCargoOwnerConstants.TYPE_CAR_OWNER);
		PageData<CarCargoOwnner> pageData = carCargoOwnerService.carCargoOwnerPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 待审批的车主分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/approvePendingCarOwnerPage")
	public ServerResponse approvePendingCarOwnerPage(
			@RequestBody @Valid RequestParams<CarCargoOwnnerPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CarCargoOwnnerPageDto dto = requestParams.getParams();
		dto.setType(CarCargoOwnerConstants.TYPE_CAR_OWNER);
		dto.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		dto.setUnstatus(CarCargoOwnerConstants.STATUS_UNCERTIFIED);
		PageData<CarCargoOwnner> pageData = carCargoOwnerService.carCargoOwnerPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 车主详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/carOwnerDetails")
	public ServerResponse carOwnerDetails(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return carCargoOwnerService.adminCarOwnerDetails(requestParams.getParams());
	}
	
	
	/**
	 * 管理员 - 修改车主免审核状态
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/updateCarOwnerAvoidAudit")
	public ServerResponse updateCarOwnerAvoidAudit(
			@RequestBody @Valid RequestParams<CarCargoOwnerUpdateAvoidAuditDto> requestParams, BindingResult result) {
		return carCargoOwnerService.carOwnerUpdateAvoidAudit(requestParams.getParams());
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
	@AdminRole
	@Valid
	@PostMapping("/approveCarOwner")
	public ServerResponse approveCarOwner(@RequestBody @Valid RequestParams<ApproveDto> requestParams,
			BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carCargoOwnerService.adminApproveCarOwner(requestParams.getParams(), user);
	}
	
	
	
	/**
	 * 管理员 - 货主分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/cargoOwnerPage")
	public ServerResponse cargoOwnerPage(@RequestBody @Valid RequestParams<CarCargoOwnnerPageDto> requestParams,
			BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CarCargoOwnnerPageDto dto = requestParams.getParams();
		dto.setType(CarCargoOwnerConstants.TYPE_CARGO_OWNER);
		PageData<CarCargoOwnner> pageData = carCargoOwnerService.carCargoOwnerPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 待审批的货主分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/approvePendingCargoOwnerPage")
	public ServerResponse approvePendingCargoOwnerPage(
			@RequestBody @Valid RequestParams<CarCargoOwnnerPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CarCargoOwnnerPageDto dto = requestParams.getParams();
		dto.setType(CarCargoOwnerConstants.TYPE_CARGO_OWNER);
		dto.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		dto.setUnstatus(CarCargoOwnerConstants.STATUS_UNCERTIFIED);
		PageData<CarCargoOwnner> pageData = carCargoOwnerService.carCargoOwnerPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 货主详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/cargoOwnerDetails")
	public ServerResponse cargoOwnerDetails(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return carCargoOwnerService.adminCargoOwnerDetails(requestParams.getParams());
	}
	
	
	/**
	 * 管理员 - 修改货主免审核状态
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/updateCargoOwnerAvoidAudit")
	public ServerResponse updateCargoOwnerAvoidAudit(
			@RequestBody @Valid RequestParams<CarCargoOwnerUpdateAvoidAuditDto> requestParams, BindingResult result) {
		return carCargoOwnerService.cargoOwnerUpdateAvoidAudit(requestParams.getParams());
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
	@AdminRole
	@Valid
	@PostMapping("/approveCargoOwner")
	public ServerResponse approveCargoOwner(@RequestBody @Valid RequestParams<ApproveDto> requestParams,
			BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carCargoOwnerService.adminApproveCargoOwner(requestParams.getParams(), user);
	}
	
	/**
	 * 管理员 - 启用车主
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Deprecated
	@AdminRole
	@Valid
	@PostMapping("/enableCarOwner")
	public ServerResponse enableCarOwner(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carCargoOwnerService.adminEnableCarOwner(requestParams.getParams(), user);
	}
	
	
	/**
	 * 管理员 - 禁用车主
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Deprecated
	@AdminRole
	@Valid
	@PostMapping("/disableCarOwner")
	public ServerResponse disableCarOwner(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carCargoOwnerService.adminDisableCarOwner(requestParams.getParams(), user);
	}
	
	
	/**
	 * 管理员 - 启用货主
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Deprecated
	@AdminRole
	@Valid
	@PostMapping("/enableCargoOwner")
	public ServerResponse enableCargoOwner(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carCargoOwnerService.adminEnableCargoOwner(requestParams.getParams(), user);
	}
	
	
	/**
	 * 管理员 - 禁用货主
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Deprecated
	@AdminRole
	@Valid
	@PostMapping("/disableCargoOwner")
	public ServerResponse disableCargoOwner(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carCargoOwnerService.adminDisableCargoOwner(requestParams.getParams(), user);
	}
	
	@AdminRole
	@Valid
	@PostMapping("/updateCargoOwnerIsActive")
	public ServerResponse updateCargoOwnerIsActive(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return carCargoOwnerService.updateCargoOwnerIsActive(requestParams.getParams());
	}
	
	@AdminRole
	@Valid
	@PostMapping("/updateCarOwnerIsActive")
	public ServerResponse updateCarOwnerIsActive(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return carCargoOwnerService.updateCarOwnerIsActive(requestParams.getParams());
	}
	
}
