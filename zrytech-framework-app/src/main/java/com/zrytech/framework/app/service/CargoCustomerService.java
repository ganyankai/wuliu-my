package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.CargoCustomerDto;
import com.zrytech.framework.app.entity.CargoCustomer;
import com.zrytech.framework.base.entity.ServerResponse;

public interface CargoCustomerService {

    /**
     * Desintion:客户注册
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户对象
     * @return:ServerResponse
     */
    ServerResponse register(CargoCustomerDto cargoCustomerDto);

    /**
     * Desintion:客户登录
     *
     * @author:jiangxiaoxiang
     * @param:DefaultCustomer
     * @return:ServerResponse
     */
    CargoCustomer findByCargoCustomerCount(CargoCustomer currentCustomer);

    /**
     * @Desinition:忘记密码
     * @Author:jxx
     * @param:requestParams
     * @return:ServerResponse
     */
    ServerResponse forget(CargoCustomerDto cargoCustomerDto);

    /**
     * @Desinition:客户修改密码(登录成功后修改)
     * @param:CargoCustomerDto
     * @Author:jxx
     * @return
     */
    ServerResponse savePassword(CargoCustomerDto cargoCustomerDto);

    /**
     * @Desinition:客户修改手机号(登录成功后修改)
     * @param:CargoCustomerDto
     * @Author:jxx
     * @return
     */
    ServerResponse updatePhone(CargoCustomerDto cargoCustomerDto);
}
