package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.entity.BillLocation;

@Service
public interface BillLocationService {

	public BillLocation assertBillLocationExist(Integer billLocationId);
	
	public void deleteByWaybillDetailId(Integer waybillDetailId);
	
}
