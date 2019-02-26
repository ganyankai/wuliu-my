package com.zry.framework.service.impl;

import javax.persistence.metamodel.Attribute;

import com.zry.framework.entity.CargoCustomer;
import com.zrytech.framework.base.entity.ServerResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import com.zry.framework.dto.CustomerPageDto;
import com.zry.framework.entity.CarSourceCar;
import com.zry.framework.entity.Customer;
import com.zry.framework.repository.CustomerRepository;
import com.zry.framework.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	

	/**
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param dto
	 * @return
	 */
	public Page<Customer> page(Integer pageNumber, Integer pageSize, CustomerPageDto dto) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(dto, customer);
		
		Sort sort = new Sort(Direction.DESC, "createDate");

		Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("carType", GenericPropertyMatchers.contains());
		
		Example<Customer> example = Example.of(customer, matcher);
		
		return customerRepository.findAll(example, pageable);
	}

    @Override
    public ServerResponse childAccountPage(CargoCustomer cargoCustomer) {
        return null;
    }
}
