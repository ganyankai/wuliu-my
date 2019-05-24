package com.zrytech.framework.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.billlocation.BillLocationAddDto;
import com.zrytech.framework.app.entity.BillLocation;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface BillLocationService {

	/**
	 * 断言运单装卸地存在
	 * @author cat
	 * 
	 * @param billLocationId	运单装卸地Id
	 * @return	运单装卸地
	 */
	public BillLocation assertBillLocationExist(Integer billLocationId);
	
	/**
	 * 删除运单项对应的运单装卸地（物理删除）
	 * @author cat
	 * 
	 * @param waybillDetailId	运单项Id
	 */
	public void deleteByWaybillDetailId(Integer waybillDetailId);
	
	/**
	 * 添加或更新运单装卸地
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	ServerResponse addBillLocation(BillLocationAddDto dto);
	
	/**
	 * 查询运单项下的全部装卸地
	 * @author cat
	 * 
	 * @param waybillDetailId	运单项Id
	 * @return
	 */
	public List<BillLocation> findByWaybillDetailId(Integer waybillDetailId);
	
}
