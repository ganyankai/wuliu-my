package com.zrytech.framework.app.service.impl;

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

import com.zrytech.framework.app.repository.CarSourceCarRepository;
import com.zrytech.framework.app.dto.CarSourceCarPageDto;
import com.zrytech.framework.app.entity.CarSourceCar;
import com.zrytech.framework.app.service.CarSourceCarService;

@Service
public class CarSourceCarServiceImpl implements CarSourceCarService {
	
	@Autowired
	private CarSourceCarRepository carSourceCarRepository;
	
	
	
	public Page<CarSourceCar> page(Integer pageNumber, Integer pageSize, CarSourceCarPageDto dto){
		
		CarSourceCar carSourceCar = new CarSourceCar();
		BeanUtils.copyProperties(dto, carSourceCar);
		
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("carType", GenericPropertyMatchers.contains());
		
		Example<CarSourceCar> example = Example.of(carSourceCar, matcher);
		
		return carSourceCarRepository.findAll(example, pageable);
	}

}
