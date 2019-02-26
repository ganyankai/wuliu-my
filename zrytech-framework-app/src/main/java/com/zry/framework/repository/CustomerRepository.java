package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zry.framework.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	
	/**
	 * 修改启用状态
	 * @author cat
	 * 
	 * @param id	司机或压货人Id
	 * @param enabled	启用状态
	 * @return
	 */
	@Modifying
	@Query("update Customer set isActive = ?2 where id = ?1")
	int updateIsActiveById(Integer id, Boolean enabled);
	
	
}
