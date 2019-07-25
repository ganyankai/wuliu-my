package com.zrytech.framework.app.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.constants.CarCargoOwnerConstants;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.service.CustomerService;
import com.zrytech.framework.base.entity.User;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.controller.core.AbstractLogin;

@RestController
@RequestMapping("/api/customer/cargoOwner/")
public class CargoOwnerCustomerLoginController extends AbstractLogin {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CarCargoOwnnerRepository carCargoOwnnerRepository;

	@Override
	protected User findByUserAccount(DefaultUser defaultUser) {
		Customer customer = customerService.assertCustomerExist(defaultUser.getUserAccount());
		Integer customerId = customer.getId();
		List<CarCargoOwnner> list = carCargoOwnnerRepository.findByCustomerIdAndType(customerId,
				CarCargoOwnerConstants.TYPE_CARGO_OWNER);
		if (list == null || list.size() == 0) {
			throw new BusinessException(112, "货主不存在");
		}
		CarCargoOwnner cargoOwner = list.get(0);
		customer.setCargoOwner(cargoOwner);
		return customer;
	}

}
