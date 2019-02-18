package com.zry.framework.dao;

import com.github.pagehelper.PageInfo;
import com.zry.framework.entity.CargoCustomer;
import com.zrytech.framework.base.entity.Page;

import java.util.List;

public interface CargoCustomerDao {
    int insertCustomer(CargoCustomer cargoCustomer);

    PageInfo<CargoCustomer> selectCustomerPage(CargoCustomer cargoCustomer, Page page);

    List<CargoCustomer> checkTelOrCount(String tel, String loginCounter);

    CargoCustomer findByCargoCustomerCount(String loginCounter);

    CargoCustomer id(Integer id);

    int update(CargoCustomer cargoCustomer);

    CargoCustomer findByTelCargoCustomer(CargoCustomer cargoCustomer);

    int forget(CargoCustomer cargo);

    int updatePhone(CargoCustomer cargoCustomer);

    int setUpEnable(Integer id, Boolean isActive);

}
