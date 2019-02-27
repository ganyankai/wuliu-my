package com.zry.framework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zry.framework.entity.CarRecordPlace;


public interface CarRecordPlaceRepository extends JpaRepository<CarRecordPlace, Integer> {
	
	
	List<CarRecordPlace> findByCarSourceId(Integer carSourceId);
	
	CarRecordPlace findByIdAndCarSourceId(Integer id, Integer carSourceId);
	
}
