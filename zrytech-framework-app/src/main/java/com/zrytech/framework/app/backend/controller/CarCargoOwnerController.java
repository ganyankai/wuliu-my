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
import com.zrytech.framework.app.dto.approve.ApproveDto;
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
		if (pageNum == null) pageNum = 1;
		if (pageSize == null) pageSize = 10;
		return carCargoOwnerService.adminCarOwnerPage(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 管理员 - 待审批的车主分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/approvePendingCarOwnerPage")
	public ServerResponse approvePendingCarOwnerPage(@RequestBody @Valid RequestParams<CarCargoOwnnerPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null) pageNum = 1;
		if (pageSize == null) pageSize = 10;
		return carCargoOwnerService.adminApprovePendingCarOwnerPage(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 管理员 - 车主详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/carOwnerDetails")
	public ServerResponse carOwnerDetails(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result) {
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
	@Valid
	@RequestMapping("/updateCarOwnerAvoidAudit")
	public ServerResponse updateCarOwnerAvoidAudit(@RequestBody @Valid RequestParams<CarCargoOwnerUpdateAvoidAuditDto> requestParams, BindingResult result) {
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
	@Valid
	@RequestMapping("/approveCarOwner")
	public ServerResponse approveCarOwner(@RequestBody @Valid RequestParams<ApproveDto> requestParams, BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carCargoOwnerService.adminApproveCarOwner(requestParams.getParams(), user);
	}
	
	
	
	////////////////////////////////////////////////////////////////
	
	
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
		if (pageNum == null) pageNum = 1;
		if (pageSize == null) pageSize = 10;
		return carCargoOwnerService.adminCargoOwnerPage(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 管理员 - 待审批的货主分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/approvePendingCargoOwnerPage")
	public ServerResponse approvePendingCargoOwnerPage(@RequestBody @Valid RequestParams<CarCargoOwnnerPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null) pageNum = 1;
		if (pageSize == null) pageSize = 10;
		return carCargoOwnerService.adminApprovePendingCargoOwnerPage(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 管理员 - 货主详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/cargoOwnerDetails")
	public ServerResponse cargoOwnerDetails(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result) {
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
	@Valid
	@RequestMapping("/updateCargoOwnerAvoidAudit")
	public ServerResponse updateCargoOwnerAvoidAudit(@RequestBody @Valid RequestParams<CarCargoOwnerUpdateAvoidAuditDto> requestParams, BindingResult result) {
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
	@Valid
	@RequestMapping("/approveCargoOwner")
	public ServerResponse approveCargoOwner(@RequestBody @Valid RequestParams<ApproveDto> requestParams, BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carCargoOwnerService.adminApproveCargoOwner(requestParams.getParams(), user);
	}
	
	
}
