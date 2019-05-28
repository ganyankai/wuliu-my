package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.CargoCustomerDto;
import com.zrytech.framework.app.dto.CertificationDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.entity.User;
import com.zrytech.framework.common.entity.SysCustomer;

@Deprecated
public interface ShipperService {

    /**
     * Desintion:如果客户跳过个人/企业资料认证,后续再填写认证资料
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
//    ServerResponse perSonOrOrganizeCertification(CertificationDto certificationDto, SysCustomer sysCustomer);

    /**
     * Desintion:认证资料详情
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
//    ServerResponse get(CertificationDto certificationDto);

    /**
     * Desintion:客户基本信息详情
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomer客户基本信息对象
     * @return:ServerResponse
     */
//    ServerResponse id(CargoCustomerDto cargoCustomerDto);

    /**
     * Desintion:客户修改头像接口
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户基本信息对象
     * @return:ServerResponse
     */
//    ServerResponse update(CargoCustomerDto cargoCustomerDto);

    /**
     * Desintion:客户启用禁用设置
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     */
//    ServerResponse enableOrUnable(CargoCustomerDto cargoCustomerDto);

    /**
     * Desintion:客户基本信息分页列表
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户基本信息对象
     * @return:ServerResponse
     */
//    ServerResponse selectCargoPage(CargoCustomerDto cargoCustomerDto, Page page);

    /**
     * Desintion:认证资料分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
//    ServerResponse certificationPage(CertificationDto certificationDto, Page page);

    /**
     * Desintion:认证资料详情
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
//    ServerResponse detail(CertificationDto certificationDto);

    /**
     * Desintion:认证资料审核
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
//    ServerResponse certificationAudit(CertificationDto certificationDto,User user);

    /**
     * Desintion:设置免审核(认证资料)
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
//    ServerResponse withOutAudit(CertificationDto certificationDto);
}
