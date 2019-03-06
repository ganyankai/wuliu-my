package com.zrytech.framework.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.entity.WaybillDetail;
import com.zrytech.framework.app.repository.WaybillDetailRepository;
import com.zrytech.framework.app.service.WaybillDetailService;
import com.zrytech.framework.base.exception.BusinessException;

@Service
public class WaybillDetailServiceImpl implements WaybillDetailService {
	
	private @Autowired WaybillDetailRepository waybillDetailRepository;

	
	/**
	 * 断言运单项存在
	 * @author cat
	 * 
	 * @param waybillDetailId	运单项Id
	 * @return	运单项
	 */
	@Override
	public WaybillDetail assertWaybillDetailExist(Integer waybillDetailId) {
        WaybillDetail waybillDetail = waybillDetailRepository.findOne(waybillDetailId);
        if (waybillDetail == null) {
            throw new BusinessException(112, "运单项不存在");
        }
        return waybillDetail;
	}
}
