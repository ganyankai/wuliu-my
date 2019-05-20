package com.zrytech.framework.app.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.CarSourceCar;
import com.zrytech.framework.base.repository.BaseRepository;


@Repository
public interface CarSourceCarRepository extends BaseRepository<CarSourceCar, Integer>{
	
	List<CarSourceCar> findByCarSourceId(Integer carSourceId);

	CarSourceCar findByIdAndCarSourceId(Integer id, Integer carSourceId);
	
	int countByCarSourceId(Integer carSourceId);
	
}
