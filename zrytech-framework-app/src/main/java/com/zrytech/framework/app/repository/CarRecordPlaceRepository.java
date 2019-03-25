package com.zrytech.framework.app.repository;

import java.util.List;

import com.zrytech.framework.app.entity.CarRecordPlace;
import com.zrytech.framework.base.repository.BaseRepository;


public interface CarRecordPlaceRepository extends BaseRepository<CarRecordPlace, Integer> {
	
	
	List<CarRecordPlace> findByCarSourceId(Integer carSourceId);
	
	CarRecordPlace findByIdAndCarSourceId(Integer id, Integer carSourceId);
	
}
