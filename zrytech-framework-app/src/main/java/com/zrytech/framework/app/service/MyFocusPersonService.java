package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.focus.MyFocusPersonAddDto;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface MyFocusPersonService {
	
	/**
	 * 货主 - 关注车主或取消车主关注
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse addOrDelFoucsCar(MyFocusPersonAddDto dto);
	
	/**
	 * 车主 - 关注货主或取消货主关注
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse addOrDelFoucsCargo(MyFocusPersonAddDto dto);
	
	/**
	 * 关注人是否关注了被关注人
	 * @author cat
	 * 
	 * @param focuserId	关注人Id
	 * @param beFocuserId	被关注人Id
	 * @return
	 */
	boolean isFocus(Integer focuserId, Integer beFocuserId);
	
	
	/**
	 * 货主 - 关注的车主列表
	 * @author cat
	 * 
	 * @return
	 */
	ServerResponse foucsCarList();
	
	
	/**
	 * 车主 - 关注的货主列表
	 * @author cat
	 * 
	 * @return
	 */
	ServerResponse foucsCargoList();
	
	
}
