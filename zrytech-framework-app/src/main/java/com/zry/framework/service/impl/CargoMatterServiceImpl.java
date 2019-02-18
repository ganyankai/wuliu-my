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

import com.zry.framework.repository.CargoMatterRepository;
import com.zry.framework.dto.CargoMatterPageDto;
import com.zry.framework.entity.CargoMatter;
import com.zry.framework.service.CargoMatterService;

@Service
public class CargoMatterServiceImpl implements CargoMatterService {

	
	@Autowired
	private CargoMatterRepository cargoMatterRepository;
	
	
	/**
	 * 报价单分页
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param dto
	 * @return
	 */
	public Page<CargoMatter> page(Integer pageNumber, Integer pageSize, CargoMatterPageDto dto){
		CargoMatter cargoMatter = new CargoMatter();
		BeanUtils.copyProperties(dto, cargoMatter);
		
		Sort sort = Sort.by(Direction.DESC, "createDate");
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("carNo", GenericPropertyMatchers.contains());
		
		Example<CargoMatter> example = Example.of(cargoMatter, matcher);
		
		return cargoMatterRepository.findAll(example, pageable);
	}
	
	
	public CargoMatter details(Integer id) {
		CargoMatter cargoMatter = cargoMatterRepository.findById(id).get();
		
		return cargoMatter;
	}

	
	
	
	
}
