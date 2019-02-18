package com.zry.framework.service.impl;

import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.price.dao.OftenAddressDao;
import com.zrytech.framework.price.dto.OftenAddressDto;
import com.zrytech.framework.price.service.OftenAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class OftenAddressServiceImpl implements OftenAddressService {

    @Autowired
    private OftenAddressDao oftenAddressDao;

    /**
     * Desintion:常用地址分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse addressPage(OftenAddressDto oftenAddressDto, Page page) {
        return null;
    }

    /**
     * Desintion:常用地址添加
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse add(OftenAddressDto oftenAddressDto) {
        return null;
    }

    /**
     * Desintion:常用地址详情
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse get(OftenAddressDto oftenAddressDto) {
        return null;
    }

    /**
     * Desintion:常用地址修改
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse update(OftenAddressDto oftenAddressDto) {
        return null;
    }

    /**
     * Desintion:常用地址删除
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse delete(OftenAddressDto oftenAddressDto) {
        return null;
    }
}
