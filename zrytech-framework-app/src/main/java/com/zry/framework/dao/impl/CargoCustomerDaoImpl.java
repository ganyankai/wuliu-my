package com.zry.framework.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.price.dao.CargoCustomerDao;
import com.zrytech.framework.price.entity.CargoCustomer;
import com.zrytech.framework.price.mapper.CargoCustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CargoCustomerDaoImpl implements CargoCustomerDao {

    @Autowired
    CargoCustomerMapper cargoCustomerMapper;

    @Override
    public int insertCustomer(CargoCustomer cargoCustomer) {
        return cargoCustomerMapper.insertCustomer(cargoCustomer);
    }

    @Override
    public PageInfo<CargoCustomer> selectCustomerPage(CargoCustomer cargoCustomer, Page page) {
        if(page==null){
            page=new Page();
        }
        return cargoCustomerMapper.selectCargoCustomerPage(cargoCustomer,page);
    }

    @Override
    public List<CargoCustomer> checkTelOrCount(String tel,String loginCounter) {
        return cargoCustomerMapper.checkTelOrCount(tel,loginCounter);
    }

    @Override
    public CargoCustomer findByCargoCustomerCount(String loginCounter) {
        return cargoCustomerMapper.findByCargoCustomerCount(loginCounter);
    }

    @Override
    public CargoCustomer id(Integer id) {
        return cargoCustomerMapper.id(id);
    }

    @Override
    public int update(CargoCustomer cargoCustomer) {
        return cargoCustomerMapper.update(cargoCustomer);
    }

    @Override
    public CargoCustomer findByTelCargoCustomer(CargoCustomer cargoCustomer) {
        return cargoCustomerMapper.findByTelCargoCustomer(cargoCustomer);
    }

    @Override
    public int forget(CargoCustomer cargo) {
        return cargoCustomerMapper.forget(cargo);
    }

    @Override
    public int updatePhone(CargoCustomer cargoCustomer) {
        return cargoCustomerMapper.updatePhone(cargoCustomer);
    }

    @Override
    public int setUpEnable(Integer id, Boolean isActive) {
        return cargoCustomerMapper.setUpEnable(id,isActive);
    }
}
