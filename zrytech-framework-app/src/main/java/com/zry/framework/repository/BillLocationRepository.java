package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.BillLocation;

import java.util.List;

@Repository
public interface BillLocationRepository extends JpaRepository<BillLocation, Integer> {
}
