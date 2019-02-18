package com.zry.framework.service.impl;

import com.github.pagehelper.PageInfo;
import com.zry.framework.constants.CargoConstant;
import com.zry.framework.dao.CargoCustomerDao;
import com.zry.framework.dao.ShipperDao;
import com.zry.framework.dto.CargoCustomerDto;
import com.zry.framework.dto.CertificationDto;
import com.zry.framework.entity.CargoCustomer;
import com.zry.framework.entity.Certification;
import com.zry.framework.service.ShipperService;
import com.zry.framework.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Customer;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ShipperServiceImpl implements ShipperService {

    @Autowired
    private ShipperDao shipperDao;

    @Autowired
    private CargoCustomerDao cargoCustomerDao;

    /**
     * Desintion:如果客户跳过个人/企业资料认证,后续再填写认证资料
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
    @Override
    public ServerResponse perSonOrOrganizeCertification(CertificationDto certificationDto, Customer customer) {
        CheckFieldUtils.checkObjecField(certificationDto.getCustomerType());
        Certification certification = BeanUtil.copy(certificationDto, Certification.class);
        CheckFieldUtils.checkObjecField(certification.getLegalerIdCardFront());
        CheckFieldUtils.checkObjecField(certification.getLegalerIdCardNo());
        CheckFieldUtils.checkObjecField(certification.getLegalerName());
        if (CargoConstant.CERTIFICATION_ORGANIZE.equalsIgnoreCase(certification.getCustomerType())) { //企业认证校验
            CheckFieldUtils.checkObjecField(certification.getName());
            CheckFieldUtils.checkObjecField(certification.getCreditCode());
            CheckFieldUtils.checkObjecField(certification.getBusinessLicense());
            CheckFieldUtils.checkObjecField(certification.getTel()
            );
        }
        //TODO:客户认证资料认证
        //设置客户ID
        certification.setCusomerId(customer.getId());
        certification.setStatus(CargoConstant.AUDIT_PROCESS);
        shipperDao.updateCertification(certification);
        return ServerResponse.success();
    }

    /**
     * Desintion:认证资料详情
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
    @Override
    public ServerResponse get(CertificationDto certificationDto) {
        Certification certification = BeanUtil.copy(certificationDto, Certification.class);
        Certification cer = shipperDao.get(certification.getId());
        return ServerResponse.successWithData(cer);
    }

    /**
     * Desintion:客户基本信息详情
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomer客户基本信息对象
     * @return:ServerResponse
     */
    @Override
    public ServerResponse id(CargoCustomerDto cargoCustomerDto) {
        CargoCustomer cargoCustomer = cargoCustomerDao.id(cargoCustomerDto.getId());
        if (cargoCustomer != null && cargoCustomer.getId() != null) {
            Certification certification = shipperDao.getCustomerId(cargoCustomer.getId());
            if (certification != null) {
                cargoCustomer.setCertification(certification);
            }
        }
        return ServerResponse.successWithData(cargoCustomer);
    }

    /**
     * Desintion:客户修改头像接口
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户基本信息对象
     * @return:ServerResponse
     */
    @Override
    public ServerResponse update(CargoCustomerDto cargoCustomerDto) {
        CargoCustomer cargoCustomer = BeanUtil.copy(cargoCustomerDto, CargoCustomer.class);
        cargoCustomerDao.update(cargoCustomer);
        return ServerResponse.success();
    }

    /**
     * Desintion:客户启用禁用设置
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse enableOrUnable(CargoCustomerDto cargoCustomerDto) {
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getIsActive());
        cargoCustomerDao.setUpEnable(cargoCustomerDto.getId(), cargoCustomerDto.getIsActive());
        return ServerResponse.success();
    }

    /**
     * Desintion:客户基本信息分页列表
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户基本信息对象
     * @return:ServerResponse
     */
    @Override
    public ServerResponse selectCargoPage(CargoCustomerDto cargoCustomerDto, Page page) {
        CargoCustomer cargoCustomer = BeanUtil.copy(cargoCustomerDto, CargoCustomer.class);
        PageInfo<CargoCustomer> pageInfo = cargoCustomerDao.selectCustomerPage(cargoCustomer, page);
        return ServerResponse.successWithData(pageInfo);
    }

    /**
     * Desintion:认证资料分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
    @Override
    public ServerResponse certificationPage(CertificationDto certificationDto, Page page) {
        Certification certification = BeanUtil.copy(certificationDto, Certification.class);
        PageInfo<Certification> pageInfo = shipperDao.certificationPage(certification, page);
        return ServerResponse.successWithData(pageInfo);
    }

    @Override
    public ServerResponse detail(CertificationDto certificationDto) {
        Certification certification = BeanUtil.copy(certificationDto, Certification.class);
        Certification cer = shipperDao.get(certification.getId());
        return ServerResponse.successWithData(cer);
    }

    @Override
    public ServerResponse certificationAudit(CertificationDto certificationDto) {
        Certification certification = shipperDao.get(certificationDto.getId());
        CargoCustomer cargoCustomer = null;
        if (certification != null) {
            cargoCustomer = cargoCustomerDao.id(certification.getCusomerId());
        }
        if (CargoConstant.AUDIT_REFUSE.equalsIgnoreCase(certificationDto.getStatus())) {
            //TODO:审核被拒绝:短信通知;describe;tel
        }
        Certification cers = BeanUtil.copy(certificationDto, Certification.class);
        //TODO:审核通过:短信通知tel
        int num = shipperDao.updateAudit(cers);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * Desintion:设置免审核(认证资料)
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
    @Override
    public ServerResponse withOutAudit(CertificationDto certificationDto) {
        CheckFieldUtils.checkObjecField(certificationDto.getAvoidAudit());
        Certification certification = BeanUtil.copy(certificationDto, Certification.class);
        int num = shipperDao.avoidAudit(certification);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }
}
