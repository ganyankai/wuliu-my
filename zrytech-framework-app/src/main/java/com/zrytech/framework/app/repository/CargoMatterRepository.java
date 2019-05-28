package com.zrytech.framework.app.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.CargoMatter;
import com.zrytech.framework.base.repository.BaseRepository;


/**
 * 报价单
 *
 */
@Repository
public interface CargoMatterRepository extends BaseRepository<CargoMatter, Integer>{

	List<CargoMatter> findByCargoIdAndCarOwnnerId(Integer cargoId, Integer carOwnnerId);
	
	int countByCargoId(Integer cargoId);
	
	List<CargoMatter> findByCargoIdAndStatus(Integer cargoId, String status);
	
}
