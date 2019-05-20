package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.OftenAddressDao;
import com.zrytech.framework.app.dto.OftenAddressDto;
import com.zrytech.framework.app.entity.OftenAddress;
import com.zrytech.framework.app.service.OftenAddressService;
import com.zrytech.framework.app.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.BeanUtil;
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
        OftenAddress oftenAddress= BeanUtil.copy(oftenAddressDto,OftenAddress.class);
        PageInfo<OftenAddress> pageInfo=oftenAddressDao.addressPage(oftenAddress,page);
        return ServerResponse.successWithData(pageInfo);
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
        CheckFieldUtils.checkObjecField(oftenAddressDto.getId());
        OftenAddress oftenAddress=oftenAddressDao.get(oftenAddressDto.getId());
        return ServerResponse.successWithData(oftenAddress);
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
        OftenAddress oftenAddress=BeanUtil.copy(oftenAddressDto,OftenAddress.class);
        int num=oftenAddressDao.update(oftenAddress);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
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
        CheckFieldUtils.checkObjecField(oftenAddressDto.getId());
        int num=oftenAddressDao.delete(oftenAddressDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }
}
