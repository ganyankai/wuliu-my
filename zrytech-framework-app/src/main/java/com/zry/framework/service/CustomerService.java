package com.zry.framework.service;

import com.zry.framework.entity.CargoCustomer;
import com.zrytech.framework.base.entity.ServerResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    ServerResponse childAccountPage(CargoCustomer cargoCustomer);
}
