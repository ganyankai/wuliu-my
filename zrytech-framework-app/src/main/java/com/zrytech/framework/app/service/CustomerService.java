package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.CargoCustomerDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    /**
     * @Desinition:子账号类列表展示
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    ServerResponse childAccountPage(CargoCustomerDto cargoCustomerDto, Page page);

    /**
     * @Desinition:子账号添加
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    ServerResponse addAccount(CargoCustomerDto cargoCustomerDto);

    /**
     * @Desinition:子账号详情
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    ServerResponse detail(CargoCustomerDto cargoCustomerDto);

    /**
     * @Desinition:子账号修改
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    ServerResponse updateAccount(CargoCustomerDto cargoCustomerDto);

    /**
     * @Desinition:子账号删除
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    ServerResponse deleteAccount(CargoCustomerDto cargoCustomerDto);
}
