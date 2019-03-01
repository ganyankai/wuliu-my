package com.zrytech.framework.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zrytech.framework.app.entity.CarSource;


public interface CarSourceRepository extends JpaRepository<CarSource, Integer>{

	
	/**
	 * 修改车源状态
	 * @author cat
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@Modifying
	@Query("update CarSource set status = ?2 where id = ?1")
	int updateStatusById(Integer id, String status);
	
}
