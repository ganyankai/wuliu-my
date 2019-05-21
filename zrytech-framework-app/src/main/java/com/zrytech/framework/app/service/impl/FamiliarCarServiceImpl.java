package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.FamiliarCarDao;
import com.zrytech.framework.app.dao.OfenLocationDao;
import com.zrytech.framework.app.dto.familiarcar.FamiliarCarDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationAddDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationCommonDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationUpdateDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.FamiliarCar;
import com.zrytech.framework.app.entity.OfenLocation;
import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.FamiliarCarRepository;
import com.zrytech.framework.app.repository.OfenLocationRepository;
import com.zrytech.framework.app.service.FamiliarCarService;
import com.zrytech.framework.app.service.OfenLocationService;
import com.zrytech.framework.app.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.BeanUtil;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FamiliarCarServiceImpl implements FamiliarCarService {

    @Autowired
    private FamiliarCarDao familiarCarDao;

    @Autowired
    private FamiliarCarRepository familiarCarRepository;

    @Autowired
    private CarCargoOwnnerRepository carCargoOwnnerRepository;

    /**
     * Desintion:关注人列表信息
     *
     * @author:dante
     * @param:familiarCar   dto
     * @return:ServerResponse
     */
//    @Override

    public ServerResponse familiarCarPage(FamiliarCarDto familiarCarDto, Page page) {
        FamiliarCar familiarCar= BeanUtil.copy(familiarCarDto,FamiliarCar.class);
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
//        货主id
//        Integer id = customer.getCargoOwner().getId();
//        ofenLocation.setCargoOwnerId(id);
        //设置关注人id
        if(customer.getCustomerType().equals("car_owner")){
            familiarCar.setCargoOwnnerId(customer.getCarOwner().getId());
        }
        if(customer.getCustomerType().equals("cargo_owner")){
            familiarCar.setCargoOwnnerId(customer.getCargoOwner().getId());
        }

        PageInfo<FamiliarCar> pageInfo=familiarCarDao.familiarCarPage(familiarCar,page);
        return ServerResponse.successWithData(pageInfo);
    }

    /**
     * Desintion:添加关注
     *
     * @author:
     * @param:FamiliarCar familiarCar dto
     * @return:ServerResponse
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ServerResponse add(FamiliarCarDto familiarCarDto) {

        FamiliarCar familiarCar = BeanUtil.copy(familiarCarDto, FamiliarCar.class);
        familiarCar.setCreateDate(new Date());

        //用户身份校验
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
        if(customer.getCustomerType().equals("car_owner")){
            if(!customer.getCarOwner().getId().equals(familiarCar.getCargoOwnnerId())){
                throw new BusinessException(new CommonResult(ResultEnum.CUSTOMER_NOT_EXIST));
            }
        }
        if(customer.getCustomerType().equals("cargo_owner")){
            if(!customer.getCargoOwner().getId().equals(familiarCar.getCargoOwnnerId())){
                throw new BusinessException(new CommonResult(ResultEnum.CUSTOMER_NOT_EXIST));
            }
        }

        //id是否存在校验
        CarCargoOwnner ownner = carCargoOwnnerRepository.findOne(familiarCar.getCargoOwnnerId());
        CarCargoOwnner ownner2 = carCargoOwnnerRepository.findOne(familiarCar.getCarOwnnerId());
        if (ownner==null || ownner2==null ){
            throw new BusinessException(new CommonResult(ResultEnum.CUSTOMER_NOT_EXIST));
        }

        if(ownner.getType().equals(ownner2.getType())){
            //相同类型不能互相关注
            throw new BusinessException(new CommonResult(ResultEnum.PARAMETER_ERROR));
        }

        //数据唯一性校验
        List<FamiliarCar> list = familiarCarRepository.findByCargoOwnnerIdAndCarOwnnerId(familiarCar.getCargoOwnnerId(), familiarCar.getCarOwnnerId());
        if (list!=null && list.size()>0){
            throw new BusinessException(new CommonResult(ResultEnum.REPOSITORY_HAS_EXIST));
        }

        familiarCarRepository.saveAndFlush(familiarCar);
        return ServerResponse.successWithData("添加成功");
    }

    /**
     * Desintion:取消关注
     *
     * @author:
     * @param:familiarCar dto
     * @return:ServerResponse
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ServerResponse delete(FamiliarCarDto familiarCarDto) {
        CheckFieldUtils.checkObjecField(familiarCarDto.getId());
        //用户身份校验
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
        if(customer.getCustomerType().equals("car_owner")){
            if(!customer.getCarOwner().getId().equals(familiarCarDto.getCargoOwnnerId())){
                throw new BusinessException(new CommonResult(ResultEnum.CUSTOMER_NOT_EXIST));
            }
        }
        if(customer.getCustomerType().equals("cargo_owner")){
            if(!customer.getCargoOwner().getId().equals(familiarCarDto.getCargoOwnnerId())){
                throw new BusinessException(new CommonResult(ResultEnum.CUSTOMER_NOT_EXIST));
            }
        }

        int num=familiarCarDao.delete(familiarCarDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

}
