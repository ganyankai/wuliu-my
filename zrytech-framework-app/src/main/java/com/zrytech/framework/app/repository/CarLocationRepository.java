package com.zrytech.framework.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.CarLocation;


@Repository
public interface CarLocationRepository extends JpaRepository<CarLocation, Integer> {

}
