package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.CarPerson;


/**
 * 车辆司机与压货人
 *
 */
@Repository
public interface CarPersonRepository extends JpaRepository<CarPerson, Integer>{
	
	/**
	 * 获取司机或压货人姓名
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "select name from CarPerson where id = ?1")
	public String findNameById(Integer id);
	
}
