package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.CarPerson;


/**
 * 车辆司机与压货人
 *
 */
@Repository
public interface CarPersonRepository extends JpaRepository<CarPerson, Integer>{
	

}
