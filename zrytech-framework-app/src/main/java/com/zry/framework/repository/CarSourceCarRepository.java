package com.zry.framework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.CarSourceCar;


@Repository
public interface CarSourceCarRepository extends JpaRepository<CarSourceCar, Integer>{
	
	List<CarSourceCar> findByCarSourceId(Integer carSourceId);

	CarSourceCar findByIdAndCarSourceId(Integer id, Integer carSourceId);
}
