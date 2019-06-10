package com.zrytech.framework.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.repository.BaseRepository;


public interface LogisticsCustomerRepository extends BaseRepository<Customer, Integer> {

	
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
	
	List<Customer> findByTel(String tel);
	
	List<Customer> findByUserAccount(String userAccount);
	
	@Query(value = "select userName from Customer where id = ?1")
	String findUserNameById(Integer id);
	
	@Query(value = "select isActive from Customer where id = ?1")
	Boolean findIsActiveById(Integer id);

	@Modifying
	@Query("update Customer set password = ?1 where id = ?2")
	int updatePassword(String password, Integer id);

}
