package com.zry.framework.service.impl;

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

import com.zry.framework.repository.BillLocationRepository;
import com.zry.framework.dto.BillLocationPageDto;
import com.zry.framework.dto.CarPersonPageDto;
import com.zry.framework.entity.BillLocation;
import com.zry.framework.entity.CarPerson;
import com.zry.framework.service.BillLocationService;

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
		
		Sort sort = Sort.by(Direction.DESC, "createDate");
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		
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
		return billLocationRepository.findById(id).get();
	}
	
	
	
	
}
