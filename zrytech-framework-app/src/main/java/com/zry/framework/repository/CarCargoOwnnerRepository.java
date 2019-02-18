package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.CarCargoOwnner;

@Repository
public interface CarCargoOwnnerRepository extends JpaRepository<CarCargoOwnner, Integer> {

	/**
	 * 获取车主或者货主企业名称
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "select name from CarCargoOwnner where id = ?1")
	public String findNameById(Integer id);
	
}
