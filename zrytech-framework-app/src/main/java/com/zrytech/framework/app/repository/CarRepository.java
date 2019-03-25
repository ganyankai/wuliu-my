package com.zrytech.framework.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.Car;
import com.zrytech.framework.base.repository.BaseRepository;


/**
 * 车辆
 *
 */
@Repository
public interface CarRepository extends BaseRepository<Car, Integer>{
	
	/**
	 * 修改车辆状态
	 * 
	 * @author cat
	 * @param id
	 * @param status
	 * @return
	 */
	@Modifying
	@Query("update Car set status = ?2 where id = ?1")
	int updateStatusById(Integer id, String status);
	
	/**
	 * 删除车辆
	 * 
	 * @author cat
	 * @param id	车辆Id
	 * @return
	 */
	@Modifying
	@Query("update Car set isDelete = true where id = ?1")
	int deleteCarById(Integer id);
	
	Car findByIdAndIsDeleteAndCarOwnerId(Integer id, Boolean isDelete, Integer carOwnerId);
	
	List<Car> findByCarNo(String carNo);
	
	
}
