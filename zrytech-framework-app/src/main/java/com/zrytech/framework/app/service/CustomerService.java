package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.CargoCustomerDto;
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
