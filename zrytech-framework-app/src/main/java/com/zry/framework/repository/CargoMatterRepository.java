package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.CargoMatter;


/**
 * 报价单
 *
 */
@Repository
public interface CargoMatterRepository extends JpaRepository<CargoMatter, Integer>{

}
