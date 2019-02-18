package com.zry.framework.service;

import com.zry.framework.dto.OftenAddressDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

public interface OftenAddressService {

    /**
     * Desintion:常用地址分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    ServerResponse addressPage(OftenAddressDto oftenAddressDto, Page page);

    /**
     * Desintion:常用地址添加
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    ServerResponse add(OftenAddressDto oftenAddressDto);

    /**
     * Desintion:常用地址详情
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    ServerResponse get(OftenAddressDto oftenAddressDto);

    /**
     * Desintion:常用地址修改
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    ServerResponse update(OftenAddressDto oftenAddressDto);

    /**
     * Desintion:常用地址删除
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    ServerResponse delete(OftenAddressDto oftenAddressDto);
}
