package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.dto.focus.MyFocusPersonAddDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.MyFocusPerson;
import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.MyFocusPersonRepository;
import com.zrytech.framework.app.service.MyFocusPersonService;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.RequestUtil;

@Service
public class MyFocusPersonServiceImpl implements MyFocusPersonService {

	@Autowired
	private MyFocusPersonRepository repository;
	
	@Autowired
	private CarCargoOwnnerRepository carCargoOwnnerRepository;

	@Autowired
	private CarCargoOwnerServiceImpl carCargoOwnerServiceImpl;

	@Transactional
	@Override
	public ServerResponse addOrDelFoucsCar(MyFocusPersonAddDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer focuserId = cargoOwner.getId();
		Integer beFocuserId = dto.getBeFocuserId();
		carCargoOwnerServiceImpl.assertCarOwnerExist(beFocuserId);
		MyFocusPerson focusPerson = repository.findByFocuserIdAndBeFocuserId(focuserId, beFocuserId);
		if (focusPerson == null) { // 暂未关注，关注
			focusPerson = new MyFocusPerson();
			focusPerson.setCreateDate(new Date());
			focusPerson.setFocuserId(focuserId);
			focusPerson.setBeFocuserId(beFocuserId);
			focusPerson.setFocusType(CargoConstant.FOCUS_CAR);
			repository.save(focusPerson);
			return ServerResponse.successWithData(true);
		} else { // 已关注，取消关注
			repository.delete(focusPerson.getId());
			return ServerResponse.successWithData(false);
		}
	}

	@Transactional
	@Override
	public ServerResponse addOrDelFoucsCargo(MyFocusPersonAddDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer focuserId = carOwner.getId();
		Integer beFocuserId = dto.getBeFocuserId();
		carCargoOwnerServiceImpl.assertCargoOwnerExist(beFocuserId);
		MyFocusPerson focusPerson = repository.findByFocuserIdAndBeFocuserId(focuserId, beFocuserId);
		if (focusPerson == null) { // 暂未关注，关注
			focusPerson = new MyFocusPerson();
			focusPerson.setCreateDate(new Date());
			focusPerson.setFocuserId(focuserId);
			focusPerson.setBeFocuserId(beFocuserId);
			focusPerson.setFocusType(CargoConstant.FOCUS_CARGO);
			repository.save(focusPerson);
			return ServerResponse.successWithData(true);
		} else { // 已关注，取消关注
			repository.delete(focusPerson.getId());
			return ServerResponse.successWithData(false);
		}
	}

	@Override
	public boolean isFocus(Integer focuserId, Integer beFocuserId) {
		MyFocusPerson focusPerson = repository.findByFocuserIdAndBeFocuserId(focuserId, beFocuserId);
		return focusPerson != null;
	}

	@Override
	public ServerResponse foucsCarList() {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer focuserId = cargoOwner.getId();
		List<MyFocusPerson> list = repository.findByFocuserId(focuserId);
		for (MyFocusPerson myFocusPerson : list) {
			String beFocuserName = carCargoOwnnerRepository.findNameById(myFocusPerson.getBeFocuserId());
			myFocusPerson.setBeFocuserName(beFocuserName);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("total", list.size());
		result.put("list", list);
		return ServerResponse.successWithData(result);
	}

	@Override
	public ServerResponse foucsCargoList() {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer focuserId = carOwner.getId();
		List<MyFocusPerson> list = repository.findByFocuserId(focuserId);
		for (MyFocusPerson myFocusPerson : list) {
			String beFocuserName = carCargoOwnnerRepository.findNameById(myFocusPerson.getBeFocuserId());
			myFocusPerson.setBeFocuserName(beFocuserName);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("total", list.size());
		result.put("list", list);
		return ServerResponse.successWithData(result);
	}

}
