package com.zrytech.framework.app.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.MyFocusLine;
import com.zrytech.framework.base.repository.BaseRepository;

@Repository
public interface MyFocusLineRepository extends BaseRepository<MyFocusLine, Integer> {

	List<MyFocusLine> findByCreateBy(Integer createBy);
	
}
