package com.zrytech.framework.app.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.WaybillDetail;
import com.zrytech.framework.base.repository.BaseRepository;


@Repository
public interface WaybillDetailRepository extends BaseRepository<WaybillDetail, Integer> {
	
	List<WaybillDetail> findByBillNo(String billNo);

}
