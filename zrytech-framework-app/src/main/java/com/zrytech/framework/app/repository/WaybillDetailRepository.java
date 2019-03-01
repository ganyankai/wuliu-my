package com.zrytech.framework.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.WaybillDetail;


@Repository
public interface WaybillDetailRepository extends JpaRepository<WaybillDetail, Integer> {
	
	List<WaybillDetail> findByBillNo(String billNo);

}
