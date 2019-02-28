package com.zrytech.framework.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.zrytech.framework.repository.BillLocationRepository;
import com.zrytech.framework.dto.BillLocationPageDto;
import com.zrytech.framework.entity.BillLocation;
import com.zrytech.framework.service.BillLocationService;

@Service
public class BillLocationServiceImpl implements BillLocationService {

	@Autowired
	private BillLocationRepository billLocationRepository;
	
	
	/**
	 * 运单装卸地分页
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param dto
	 * @return
	 */
	public Page<BillLocation> page(Integer pageNumber, Integer pageSize, BillLocationPageDto dto){
		BillLocation billLocation = new BillLocation();
		BeanUtils.copyProperties(dto, billLocation);
		
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("name", GenericPropertyMatchers.contains());
		
		Example<BillLocation> example = Example.of(billLocation, matcher);
		
		return billLocationRepository.findAll(example, pageable);
	}
	
	
	/**
	 * 运单装卸地详情
	 * 
	 * @param id
	 * @return
	 */
	public BillLocation details(Integer id) {
		return billLocationRepository.findOne(id);
	}
	
	
	
	
}
