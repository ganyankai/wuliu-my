package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.oftenaddress.OftenAddressAddDto;
import com.zrytech.framework.app.dto.oftenaddress.OftenAddressQueryDto;
import com.zrytech.framework.app.dto.oftenaddress.OftenAddressUpdateDto;
import com.zrytech.framework.app.entity.OftenAddress;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

public interface OftenAddressService {

	/**
	 * 车主货主 - 添加常用路线
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse save(OftenAddressAddDto dto);
	
	/**
	 * 车主货主 - 删除常用路线
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse delete(CommonDto dto);
	
	/**
	 * 车主货主 - 修改常用路线
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse update(OftenAddressUpdateDto dto);
	
	/**
	 * 车主货主 - 常用路线列表
	 * @author cat
	 * 
	 * @return
	 */
	ServerResponse list();
	
	/**
	 * 车主货主 - 常用路线详情
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse details(CommonDto dto);
	
	/**
	 * 确认常用路线存在且属于当前登录人
	 * @author cat
	 * 
	 * @param addressId	常用路线Id
	 * @return
	 */
	OftenAddress assertBelongToCurrentCustomer(Integer addressId);

	ServerResponse addressPage(Integer pageNum, Integer pageSize, OftenAddressQueryDto params);
}
