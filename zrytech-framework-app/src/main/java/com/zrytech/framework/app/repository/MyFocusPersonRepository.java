package com.zrytech.framework.app.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.MyFocusPerson;
import com.zrytech.framework.base.repository.BaseRepository;

@Repository
public interface MyFocusPersonRepository extends BaseRepository<MyFocusPerson, Integer> {

	MyFocusPerson findByFocuserIdAndBeFocuserId(Integer focuserId, Integer beFocuserId);
	
	List<MyFocusPerson> findByFocuserId(Integer focuserId);

}
