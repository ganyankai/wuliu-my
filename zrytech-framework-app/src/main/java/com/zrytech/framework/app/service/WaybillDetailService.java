package com.zrytech.framework.app.service;

import com.zrytech.framework.base.entity.ServerResponse;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.entity.WaybillDetail;

@Service
public interface WaybillDetailService {

	public WaybillDetail assertWaybillDetailExist(Integer waybillDetailId);


	ServerResponse getCarWbdList(Integer pageNum, Integer pageSize,Integer carId);


	ServerResponse getCargoWbdList(Integer pageNum, Integer pageSize,Integer cargoId);

}
