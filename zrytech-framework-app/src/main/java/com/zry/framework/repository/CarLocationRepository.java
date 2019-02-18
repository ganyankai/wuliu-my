package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.CarLocation;


@Repository
public interface CarLocationRepository extends JpaRepository<CarLocation, Integer> {

}
