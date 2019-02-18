package com.zry.framework.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.price.entity.CargoCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CargoCustomerMapper {
    int insertCustomer(CargoCustomer cargoCustomer);

    PageInfo<CargoCustomer> selectCargoCustomerPage(@Param("cargoCustomer") CargoCustomer cargoCustomer, Page page);

    List<CargoCustomer> checkTelOrCount(@Param("tel") String tel, @Param("loginCounter") String loginCounter);

    CargoCustomer findByCargoCustomerCount(@Param("loginCounter") String loginCounter);

    CargoCustomer id(@Param("id") Integer id);

    int update(CargoCustomer cargoCustomer);

    CargoCustomer findByTelCargoCustomer(CargoCustomer cargoCustomer);

    int forget(CargoCustomer cargo);

    int updatePhone(CargoCustomer cargoCustomer);

    int setUpEnable(@Param("id") Integer id, @Param("isActive") Boolean isActive);
}
