package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.BillLocation;

@Repository
public interface BillLocationRepository extends JpaRepository<BillLocation, Integer> {

}
