package com.zrytech.framework.app.repository;

import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.CarLocation;
import com.zrytech.framework.base.repository.BaseRepository;


@Repository
public interface CarLocationRepository extends BaseRepository<CarLocation, Integer> {

}
