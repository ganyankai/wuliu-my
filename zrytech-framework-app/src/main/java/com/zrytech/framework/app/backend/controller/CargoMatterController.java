package com.zrytech.framework.app.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.dto.CargoMatterPageDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.entity.CargoMatter;
import com.zrytech.framework.app.service.CargoMatterService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.User;

/**
 * 后台管理系统 - 报价单
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/admin/cargoMatter")
public class CargoMatterController {

	@Autowired
	private CargoMatterService cargoMatterService;

	
	/**
	 * 管理员 - 报价单分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CargoMatterPageDto> requestParams,
			BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		PageData<CargoMatter> pageData = cargoMatterService.cargoMatterPage(requestParams.getParams(), pageNum,
				pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 待审批的报价单分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/approvePendingPage")
	public ServerResponse approvePendingPage(@RequestBody @Valid RequestParams<CargoMatterPageDto> requestParams,
			BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CargoMatterPageDto dto = requestParams.getParams();
		dto.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		PageData<CargoMatter> pageData = cargoMatterService.cargoMatterPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 某一个车主的报价单分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/oneCarOwnerCargoMatterPage")
	public ServerResponse oneCarOwnerCargoMatterPage(@RequestBody @Valid RequestParams<CargoMatterPageDto> requestParams,
			BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CargoMatterPageDto dto = requestParams.getParams();
		if(dto.getCarOwnerId() == null) {
			throw new BusinessException(112, "车主不能为空");
		}
		PageData<CargoMatter> pageData = cargoMatterService.cargoMatterPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 某一个货主的报价单分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/oneCargoOwnerCargoMatterPage")
	public ServerResponse oneCargoOwnerCargoMatterPage(@RequestBody @Valid RequestParams<CargoMatterPageDto> requestParams,
			BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CargoMatterPageDto dto = requestParams.getParams();
		if(dto.getCargoOwnerId() == null) {
			throw new BusinessException(112, "货主不能为空");
		}
		PageData<CargoMatter> pageData = cargoMatterService.cargoMatterPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 报价单详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/details")
	public ServerResponse details(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result) {
		return cargoMatterService.adminDetails(requestParams.getParams());
	}
	
	
	/**
	 * 管理员 - 报价单详情审批
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/approve")
	public ServerResponse approve(@RequestBody @Valid RequestParams<ApproveDto> requestParams, BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return cargoMatterService.adminApprove(requestParams.getParams(), user);
	}
}
