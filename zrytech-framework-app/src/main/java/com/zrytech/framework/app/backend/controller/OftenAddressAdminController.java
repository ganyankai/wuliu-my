package com.zrytech.framework.app.backend.controller;

import com.zrytech.framework.app.dto.oftenaddress.OftenAddressQueryDto;
import com.zrytech.framework.app.service.OftenAddressService;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 常用路线
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/admin/usedAddress")
public class OftenAddressAdminController {

	@Autowired
	private OftenAddressService service;

	@Valid
	@PostMapping("/page")
	public ServerResponse addressPage(@RequestBody RequestParams<OftenAddressQueryDto> requestParams, BindingResult result) {
		if (requestParams.getParams() == null) {
			throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
		}
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
		return service.addressPage(pageNum, pageSize,requestParams.getParams());
	}


}
