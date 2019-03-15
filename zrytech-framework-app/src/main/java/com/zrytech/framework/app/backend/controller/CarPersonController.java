package com.zrytech.framework.app.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.carperson.CarPersonPageDto;
import com.zrytech.framework.app.service.CarPersonService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.User;

/**
 * 后台管理系统 - 司机与压货人
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/admin/carPerson")
public class CarPersonController {

	@Autowired
	private CarPersonService carPersonService;

	/**
	 * 司机与压货人分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CarPersonPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		return carPersonService.page(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 司机与压货人详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/details")
	public ServerResponse details(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result) {
		return carPersonService.details(requestParams.getParams());
	}

	
	/**
	 * 司机与压货人审核
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@PostMapping("/check")
	public ServerResponse check(@RequestBody @Valid RequestParams<CheckDto> requestParams, BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carPersonService.check(requestParams.getParams(), user);
	}
	
	
	/**
	 * 管理员 - 审批司机或压货人
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/approve")
	public ServerResponse approve(@RequestBody @Valid RequestParams<CheckDto> requestParams, BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carPersonService.approve(requestParams.getParams(), user);
	}
	
	
}
