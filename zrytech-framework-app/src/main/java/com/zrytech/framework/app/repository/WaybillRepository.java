package com.zrytech.framework.app.repository;

import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.Waybill;
import com.zrytech.framework.base.repository.BaseRepository;

@Repository
public interface WaybillRepository extends BaseRepository<Waybill, Integer> {

	Waybill findByBillNo(String billNo);
	
}
