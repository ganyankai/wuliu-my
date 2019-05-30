package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.focus.MyFocusLineAddDto;
import com.zrytech.framework.app.dto.focus.MyFocusLineUpdateDto;
import com.zrytech.framework.app.entity.MyFocusLine;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface MyFocusLineService {
	
	/**
	 * 车主货主 - 添加关注的路线
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse save(MyFocusLineAddDto dto);
	
	/**
	 * 车主货主 - 修改关注的路线
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse update(MyFocusLineUpdateDto dto);
	
	/**
	 * 车主货主 - 删除关注的路线
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse delete(CommonDto dto);
	
	/**
	 * 车主货主 - 关注的路线列表
	 * @author cat
	 * 
	 * @return
	 */
	ServerResponse list();
	
	/**
	 * 车主货主 - 关注的路线详情
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse details(CommonDto dto);
	
	/**
	 * 确认关注的路线存在且属于当前登录人
	 * @author cat
	 * 
	 * @param lineId	关注的路线Id
	 * @return
	 */
	MyFocusLine assertBelongToCurrentCustomer(Integer lineId);
	
}
