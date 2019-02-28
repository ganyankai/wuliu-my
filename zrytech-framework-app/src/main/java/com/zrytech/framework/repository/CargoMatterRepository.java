package com.zrytech.framework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.entity.CargoMatter;


/**
 * 报价单
 *
 */
@Repository
public interface CargoMatterRepository extends JpaRepository<CargoMatter, Integer>{

	List<CargoMatter> findByCargoIdAndCarOwnnerId(Integer cargoId, Integer carOwnnerId);
	
}
