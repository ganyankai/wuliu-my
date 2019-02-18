package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.Car;


/**
 * 车辆
 *
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
	

}
