package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.oftenaddress.OftenAddressAddDto;
import com.zrytech.framework.app.dto.oftenaddress.OftenAddressQueryDto;
import com.zrytech.framework.app.dto.oftenaddress.OftenAddressUpdateDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.OftenAddress;
import com.zrytech.framework.app.mapper.OftenAddressMapper;
import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.OftenAddressRepository;
import com.zrytech.framework.app.service.OftenAddressService;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OftenAddressServiceImpl implements OftenAddressService {

	@Autowired
	private OftenAddressMapper mapper;
	
    @Autowired
    private OftenAddressRepository repository;

    @Autowired
	private CarCargoOwnnerRepository carCargoOwnnerRepository;

	@Transactional
	@Override
	public ServerResponse save(OftenAddressAddDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		OftenAddress address = new OftenAddress();
		BeanUtils.copyProperties(dto, address);
		address.setCustomerId(customer.getId());
		
		Example<OftenAddress> example = Example.of(address);
		List<OftenAddress> list = repository.findAll(example);
		if (!list.isEmpty()) {
			throw new BusinessException(112, "常用路线已存在");
		}
		
		address.setCreateDate(new Date());
		repository.save(address);
		return ServerResponse.successWithData(address);
	}
	
	@Transactional
	@Override
	public ServerResponse delete(CommonDto dto) {
		this.assertBelongToCurrentCustomer(dto.getId());
		repository.delete(dto.getId());
		return ServerResponse.success();
	}
	
	@Transactional
	@Override
	public ServerResponse update(OftenAddressUpdateDto dto) {
		OftenAddress address = this.assertBelongToCurrentCustomer(dto.getId());
		
		OftenAddress temp = new OftenAddress();
		BeanUtils.copyProperties(dto, temp);
		temp.setId(null);
		temp.setCustomerId(address.getCustomerId());
		Example<OftenAddress> example = Example.of(temp);
		List<OftenAddress> list = repository.findAll(example);
		if (!list.isEmpty()) {
			throw new BusinessException(112, "常用路线已存在");
		}
		
		BeanUtils.copyProperties(dto, address);
		repository.save(address);
		return ServerResponse.successWithData(address);
	}
	
	public ServerResponse list() {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		List<OftenAddress> list = mapper.selectByCustomerId(customer.getId());
		Map<String, Object> result = new HashMap<>();
		result.put("total", list.size());
		result.put("list", list);
		return ServerResponse.successWithData(result);
	}
	
	@Override
	public ServerResponse details(CommonDto dto) {
		OftenAddress address = this.assertBelongToCurrentCustomer(dto.getId());
		return ServerResponse.successWithData(address);
	}
	
	@Override
	public OftenAddress assertBelongToCurrentCustomer(Integer addressId) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		OftenAddress address = repository.findOne(addressId);
		if (address == null) {
			throw new BusinessException(112, "常用路线不存在");
		}
		if (!address.getCustomerId().equals(customer.getId())) {
			throw new BusinessException(112, "常用路线不存在");
		}
		return address;
	}

	@Override
	public ServerResponse addressPage(Integer pageNum, Integer pageSize, OftenAddressQueryDto dto) {
		//取得车主id或者货主id
		Integer id = dto.getId();
		CarCargoOwnner ownner = carCargoOwnnerRepository.findOne(id);
		if(ownner==null){
			throw new BusinessException(112, "用户不存在");
		}
		Integer customerId = ownner.getCustomerId();

		com.github.pagehelper.Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<OftenAddress> list = mapper.selectByCustomerId(customerId);
		PageData<OftenAddress> pageData = new PageData<>(page.getPageSize(), page.getPageNum(), page.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}


}
