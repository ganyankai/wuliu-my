package com.zrytech.framework.service.impl;

import com.zrytech.framework.constants.CargoConstant;
import com.zrytech.framework.dao.CargoCustomerDao;
import com.zrytech.framework.dao.OftenAddressDao;
import com.zrytech.framework.dao.ShipperDao;
import com.zrytech.framework.dto.CargoCustomerDto;
import com.zrytech.framework.entity.CargoCustomer;
import com.zrytech.framework.entity.Certification;
import com.zrytech.framework.entity.OftenAddress;
import com.zrytech.framework.enums.LogisticsResult;
import com.zrytech.framework.enums.LogisticsResultEnum;
import com.zrytech.framework.service.CargoCustomerService;
import com.zrytech.framework.utils.CheckFieldUtils;
import com.zrytech.framework.utils.PasswordUtils;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.BeanUtil;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CargoCustomerServiceImpl implements CargoCustomerService {


    @Autowired
    private CargoCustomerDao cargoCustomerDao;

    @Autowired
    private OftenAddressDao oftenAddressDao;

    @Autowired
    private ShipperDao shipperDao;

    /**
     * Desintion:客户注册
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户对象
     * @return:ServerResponse
     */
    @Override
    public ServerResponse register(CargoCustomerDto cargoCustomerDto) {
        //字段非空校验
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getLoginCounter());
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getTel());
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getPhoneCode());
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getPwd());
        CargoCustomer cargoCustomer = BeanUtil.copy(cargoCustomerDto, CargoCustomer.class);
        //TODO:密码校验
        //TODO:手机号去重校验和账号校验
        List<CargoCustomer> checkTelList = cargoCustomerDao.checkTelOrCount(cargoCustomer.getTel(), null);
        if (checkTelList != null && checkTelList.size() > 0) {
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.PHONE_EXISTED));
        }
        List<CargoCustomer> checkLoginCounterList = cargoCustomerDao.checkTelOrCount(null, cargoCustomer.getLoginCounter());
        if (checkLoginCounterList != null && checkLoginCounterList.size() > 0) {
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.LOGIN_COUNTER_EXISTED));
        }
        cargoCustomer.setCreateBy(0);
        cargoCustomer.setCreateDate(new Date());
        cargoCustomer.setIsActive(false);
        cargoCustomer.setPwd(PasswordUtils.encryptStringPassword(cargoCustomer.getPwd(),cargoCustomer.getLoginCounter()));
        //TODO:短信验证码校验
        int num = cargoCustomerDao.insertCustomer(cargoCustomer);
        CheckFieldUtils.assertSuccess(num);
        //常用提货地址和接货地址
        List<OftenAddress> list = cargoCustomerDto.getExtractList();
        if (list != null && list.size() > 0) {
            //批量添加常用地址
            oftenAddressDao.batchSave(cargoCustomer.getId(), list, new Date());
        }
        //添加认证资料
        Certification certification = cargoCustomerDto.getCertificationData();
        if (certification == null) {
            certification = new Certification();
            certification.setStatus(CargoConstant.AUDIT_PROCESS);
            certification.setCusomerId(cargoCustomer.getId());
            certification.setCreateDate(new Date());
        } else {
            certification.setStatus(CargoConstant.AUDIT_PROCESS);
            certification.setCusomerId(cargoCustomer.getId());
            certification.setCreateDate(new Date());
        }
        shipperDao.save(certification);
        return ServerResponse.success();
    }

    /**
     * Desintion:客户登录
     *
     * @author:jiangxiaoxiang
     * @param:DefaultCustomer
     * @return:ServerResponse
     */
    @Override
    public CargoCustomer findByCargoCustomerCount(CargoCustomer currentCustomer) {
        CheckFieldUtils.checkObjecField(currentCustomer.getLoginCounter());
        CargoCustomer cargo = cargoCustomerDao.findByCargoCustomerCount(currentCustomer.getLoginCounter());
        if (cargo == null) {
            throw new BusinessException(new CommonResult(ResultEnum.CUSTOMER_NOT_EXIST));
        }
        if (cargo != null && cargo.getId() != null) {
            Certification certification = shipperDao.getCustomerId(cargo.getId());
            if (certification != null) {
                cargo.setCertification(certification);
            }
        }
        return cargo;
    }

    /**
     * @Desinition:忘记密码
     * @Author:jxx
     * @param:requestParams
     * @return:ServerResponse
     */
    @Override
    public ServerResponse forget(CargoCustomerDto cargoCustomerDto) {
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getPhoneCode());
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getPwd());
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getConfirmPwd());
        //TODO 短信验证码验证(手机号验证)
        //this.sendCode(cargoCustomerDto.getTel(), cargoCustomerDto.getPhoneCode(), com.zrytech.framework.link.enums.ResultEnum.UPDATE_CODE.getCode());
        if (!cargoCustomerDto.getPwd().equals(cargoCustomerDto.getConfirmPwd())) {
            throw new BusinessException(new CommonResult(ResultEnum.Not_AGREE));
        }
        CargoCustomer cargoCustomer = BeanUtil.copy(cargoCustomerDto, CargoCustomer.class);
        CargoCustomer cargo = cargoCustomerDao.findByTelCargoCustomer(cargoCustomer);
        if (cargo == null) {
            throw new BusinessException(new CommonResult(ResultEnum.CUSTOMER_NOT_EXIST));
        }
        cargo.setPwd(PasswordUtils.encryptStringPassword(cargo.getPwd(), cargo.getLoginCounter()));
        int num = cargoCustomerDao.forget(cargo);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * @return
     * @Desinition:客户修改密码(登录成功后修改)
     * @param:CargoCustomerDto
     * @Author:jxx
     */
    @Override
    public ServerResponse savePassword(CargoCustomerDto cargoCustomerDto) {
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getPwd());
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getConfirmPwd());
        if (!cargoCustomerDto.getPwd().equals(cargoCustomerDto.getConfirmPwd())) {
            throw new BusinessException(new CommonResult(ResultEnum.Not_AGREE));
        }
        CargoCustomer cargoCustomer = cargoCustomerDao.id(cargoCustomerDto.getId());
        if (cargoCustomer != null) {
            cargoCustomer.setPwd(PasswordUtils.encryptStringPassword(cargoCustomerDto.getPwd(), cargoCustomer.getLoginCounter()));
        }
        int num = cargoCustomerDao.forget(cargoCustomer);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * @return
     * @Desinition:客户修改手机号(登录成功后修改)
     * @param:CargoCustomerDto
     * @Author:jxx
     */
    @Override
    public ServerResponse updatePhone(CargoCustomerDto cargoCustomerDto) {
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getPhoneCode());
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getTel());
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getPwd());
        //TODO:短信验证码验证
        CargoCustomer cargoCustomer = cargoCustomerDao.id(cargoCustomerDto.getId());
        String getPwd = PasswordUtils.encryptStringPassword(cargoCustomerDto.getPwd(), cargoCustomer.getLoginCounter());
        if (!getPwd.equals(cargoCustomer.getPwd())) {
            throw new BusinessException(new CommonResult(ResultEnum.Not_AGREE));
        }
        cargoCustomer.setTel(cargoCustomerDto.getTel());
        int num = cargoCustomerDao.updatePhone(cargoCustomer);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }
}
