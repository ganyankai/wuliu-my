package com.zrytech.framework.app.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.BillLocation;
import com.zrytech.framework.base.repository.BaseRepository;

@Repository
public interface BillLocationRepository extends BaseRepository<BillLocation, Integer> {
	
	List<BillLocation> findByWaybillDetailId(Integer waybillDetailId);
	
}



