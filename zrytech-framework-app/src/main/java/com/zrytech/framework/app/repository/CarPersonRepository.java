package com.zrytech.framework.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.CarPerson;


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
	
	
	/**
	 * 删除司机或压货人
	 * @author cat
	 * 
	 * @param id	司机或压货人Id
	 * @return
	 */
	@Modifying
	@Query("update CarPerson set isDelete = true where id = ?1")
	public int deleteCarPersonById(Integer id);
	
	
	/**
	 * 修改司机或压货人状态
	 * @author cat
	 * 
	 * @param id	司机或压货人Id
	 * @param status	状态
	 * @return
	 */
	@Modifying
	@Query("update CarPerson set status = ?2 where id = ?1")
	public int updateStatusById(Integer id, String status);
	
	
	CarPerson findByIdAndIsDeleteAndCarOwnerId(Integer id, Boolean isDelete, Integer carOwnerId);
	
}
