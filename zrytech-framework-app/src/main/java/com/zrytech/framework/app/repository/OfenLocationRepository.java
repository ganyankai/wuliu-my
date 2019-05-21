package com.zrytech.framework.app.repository;

import com.zrytech.framework.app.entity.OfenLocation;
import com.zrytech.framework.base.repository.BaseRepository;

import java.util.List;

public interface OfenLocationRepository extends BaseRepository<OfenLocation, Integer> {
    List<OfenLocation> findByCargoOwnerId(Integer cargoOwnerId);
}
