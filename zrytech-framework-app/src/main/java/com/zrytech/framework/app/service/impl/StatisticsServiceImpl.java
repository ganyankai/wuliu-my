package com.zrytech.framework.app.service.impl;

import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.Statistics;
import com.zrytech.framework.app.mapper.StatisticsMapper;
import com.zrytech.framework.app.service.StatisticsService;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public ServerResponse count(Integer id) {
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
        ServerResponse response = null;
        if(customer.getCustomerType().equalsIgnoreCase("car_owner")){
            Integer carId = customer.getCarOwner().getId();
            //车主
            response =  getStatisticsCar(carId);

        }else if(customer.getCustomerType().equalsIgnoreCase("cargo_owner")){
            //货主
            Integer cargoId = customer.getCarOwner().getId();
            response =  getStatisticsCargo(cargoId);
        }
        return response;
    }

    public ServerResponse getStatisticsCar(Integer carId) {
        Statistics statistics = new Statistics();
        //定位中心的数量
        statistics.setLocCarCount(statisticsMapper.locCarCountCar(carId));
        //推荐货源的数量
        statistics.setRecCargoCount(statisticsMapper.recCargoCountCar(carId));
        //关注货主的数量
        statistics.setFollowCargoOwnerCount(statisticsMapper.followCargoOwnerCountCar(carId));
        return ServerResponse.successWithData(statistics);
    }

    public ServerResponse getStatisticsCargo(Integer cargoId) {
        Statistics statistics = new Statistics();
        //定位中心的数量
        statistics.setLocCarCount(statisticsMapper.locCarCountCargo(cargoId));
        //关注车主的数量
        statistics.setFollowCarOwnerCount(statisticsMapper.followCarOwnerCountCar(cargoId));
        //熟车的数量
        statistics.setFimiliarCarCount(statisticsMapper.fimiliarCarCountCargo(cargoId));
        return ServerResponse.successWithData(statistics);
    }

}
