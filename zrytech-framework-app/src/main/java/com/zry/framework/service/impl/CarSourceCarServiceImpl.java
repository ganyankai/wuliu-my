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

import com.zry.framework.repository.CarSourceCarRepository;
import com.zry.framework.dto.CarSourceCarPageDto;
import com.zry.framework.dto.CarSourcePageDto;
import com.zry.framework.entity.CarLocation;
import com.zry.framework.entity.CarSource;
import com.zry.framework.entity.CarSourceCar;
import com.zry.framework.service.CarSourceCarService;

@Service
public class CarSourceCarServiceImpl implements CarSourceCarService {
	
	@Autowired
	private CarSourceCarRepository carSourceCarRepository;
	
	
	
	public Page<CarSourceCar> page(Integer pageNumber, Integer pageSize, CarSourceCarPageDto dto){
		
		CarSourceCar carSourceCar = new CarSourceCar();
		BeanUtils.copyProperties(dto, carSourceCar);
		
		Sort sort = Sort.by(Direction.DESC, "createDate");
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("carType", GenericPropertyMatchers.contains());
		
		Example<CarSourceCar> example = Example.of(carSourceCar, matcher);
		
		return carSourceCarRepository.findAll(example, pageable);
	}

}
