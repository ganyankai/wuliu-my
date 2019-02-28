package com.zrytech.framework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.entity.BillLocation;

@Repository
public interface BillLocationRepository extends JpaRepository<BillLocation, Integer> {
	
	List<BillLocation> findByWaybillDetailId(Integer waybillDetailId);
	
}



