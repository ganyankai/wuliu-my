package com.zrytech.framework.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.BillLocation;
import com.zrytech.framework.base.repository.BaseRepository;

@Repository
public interface BillLocationRepository extends BaseRepository<BillLocation, Integer> {

	List<BillLocation> findByWaybillDetailId(Integer waybillDetailId);

	
	BillLocation findByWaybillIdAndWaybillDetailIdAndCargoLocationId(Integer waybillId, Integer waybillDetailId,
			Integer cargoLocationId);

	
	@Query("select sum(qty) from BillLocation where waybillId = ?1 and cargoLocationId = ?2")
	Integer countQtyByWaybillIdAndCargoLocationId(Integer waybillId, Integer cargoLocationId);
	
	@Modifying
	@Query("delete from BillLocation where waybillDetailId = ?1")
	void deleteByWaybillDetailId(Integer waybillDetailId);
	
}
