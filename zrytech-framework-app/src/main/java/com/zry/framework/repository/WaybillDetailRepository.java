package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.WaybillDetail;


@Repository
public interface WaybillDetailRepository extends JpaRepository<WaybillDetail, Integer> {

}
