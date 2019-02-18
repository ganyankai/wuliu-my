package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.CarSourceCar;


@Repository
public interface CarSourceCarRepository extends JpaRepository<CarSourceCar, Integer>{

}
