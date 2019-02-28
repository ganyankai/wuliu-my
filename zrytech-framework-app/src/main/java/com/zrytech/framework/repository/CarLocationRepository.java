package com.zrytech.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.entity.CarLocation;


@Repository
public interface CarLocationRepository extends JpaRepository<CarLocation, Integer> {

}
