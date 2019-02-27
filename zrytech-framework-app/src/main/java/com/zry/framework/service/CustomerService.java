package com.zry.framework.service;

import com.zry.framework.dto.CargoCustomerDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    ServerResponse childAccountPage(CargoCustomerDto cargoCustomerDto, Page page);

    ServerResponse addAccount(CargoCustomerDto cargoCustomerDto);

    ServerResponse detail(CargoCustomerDto cargoCustomerDto);

    ServerResponse updateAccount(CargoCustomerDto cargoCustomerDto);

    ServerResponse deleteAccount(CargoCustomerDto cargoCustomerDto);
}
