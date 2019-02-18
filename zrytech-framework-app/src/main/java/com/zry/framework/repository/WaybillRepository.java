package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.Waybill;

@Repository
public interface WaybillRepository extends JpaRepository<Waybill, Integer> {

}
