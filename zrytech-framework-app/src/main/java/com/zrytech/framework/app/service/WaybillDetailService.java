package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.entity.WaybillDetail;

@Service
public interface WaybillDetailService {

	public WaybillDetail assertWaybillDetailExist(Integer waybillDetailId);
	
}
