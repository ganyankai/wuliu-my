package com.zrytech.framework.app.repository;

import com.zrytech.framework.base.repository.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.Waybill;

@Repository
public interface WaybillRepository extends BaseRepository<Waybill,Integer> {

	Waybill findByBillNo(String billNo);
	
	@Modifying
	@Query("update Waybill set status = ?2 where id = ?1")
	int updateStatusById(Integer id, String status);
	
}
