package com.zrytech.framework.app.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.Loading;
import com.zrytech.framework.base.repository.BaseRepository;

@Repository
public interface CargoLocationRepository extends BaseRepository<Loading, Integer> {
	
	List<Loading> findByCargoId(Integer cargoId);

}
