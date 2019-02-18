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

import com.zry.framework.repository.CarPersonRepository;
import com.zry.framework.dto.CarPersonPageDto;
import com.zry.framework.dto.CheckDto;
import com.zry.framework.entity.CarPerson;
import com.zry.framework.service.CarPersonService;

@Service
public class CarPersonServiceImpl implements CarPersonService {
	
	@Autowired
	private CarPersonRepository carPersonRepository;

	
	/**
	 * 司机与压货人分页
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param dto
	 * @return
	 */
	@Override
	public Page<CarPerson> page(Integer pageNumber, Integer pageSize, CarPersonPageDto dto){
		CarPerson carPerson = new CarPerson();
		BeanUtils.copyProperties(dto, carPerson);
		
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("name", GenericPropertyMatchers.contains());
		
		Example<CarPerson> example = Example.of(carPerson, matcher);
		
		return carPersonRepository.findAll(example, pageable);
	}
	
	
	
	public CarPerson details(Integer id) {
		CarPerson carPerson = carPersonRepository.findOne(id);
		
		return carPerson;
	}
	
	
	/**
	 * 审核
	 * @param checkDto
	 */
	public void check(CheckDto checkDto) {
		
		
	}
	
}
