package com.zrytech.framework.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.base.repository.BaseRepository;

@Repository
public interface CarCargoOwnnerRepository extends BaseRepository<CarCargoOwnner, Integer> {

	/**
	 * 获取车主或者货主企业名称
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "select name from CarCargoOwnner where id = ?1")
	public String findNameById(Integer id);
	
	@Query(value = "select id from CarCargoOwnner where name like CONCAT('%', ?1, '%')")
	public List<Integer> findIdByName(String name);
	
	
	/**
	 * 修改车主货主免审核状态
	 * @author cat
	 * 
	 * @param avoidAudit	是否免审核
	 * @param id	车主货主id
	 */
	@Transactional
	@Modifying
	@Query(value = "update CarCargoOwnner set avoidAudit = ?1 where id = ?2")
	public void updateAvoidAuditById(Boolean avoidAudit, Integer id);
	
	
	public CarCargoOwnner findByIdAndType(Integer id, String type);
	
}

