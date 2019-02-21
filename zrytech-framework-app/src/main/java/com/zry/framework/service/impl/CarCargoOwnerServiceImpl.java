package com.zry.framework.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.zry.framework.entity.Car;
import com.zry.framework.entity.CarCargoOwnner;
import com.zry.framework.repository.CarCargoOwnnerRepository;
import com.zry.framework.service.CarCargoOwnerService;

@Service
public class CarCargoOwnerServiceImpl implements CarCargoOwnerService {


	@Autowired private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	
}
