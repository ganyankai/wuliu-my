package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.OftenAddressDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationAddDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationCommonDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationUpdateDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

public interface OfenLocationService {

    /**
     * Desintion:常用地址分页列表信息
     *
     * @author:dante
     * @param:货主常用地址dto
     * @return:ServerResponse
     */
    ServerResponse addressPage(OfenLocationDto ofenLocationDto, Page page);

    /**
     * Desintion:货主常用地址添加
     *
     * @param:常用地址dto
     * @return:ServerResponse
     */
    ServerResponse add(OfenLocationAddDto ofenLocationAddDto);

    /**
     * Desintion:货主常用地址详情
     *
     * @author:dante
     * @param:货主常用地址dto
     * @return:ServerResponse
     */
    ServerResponse get(OfenLocationCommonDto ofenLocationCommonDto);

    /**
     * Desintion:货主常用地址修改
     *
     * @author:dante
     * @param:货主常用地址dto
     * @return:ServerResponse
     */
    ServerResponse update(OfenLocationUpdateDto ofenLocationUpdateDto);

    /**
     * Desintion:货主常用地址删除
     *
     * @author:dante
     * @param:货主常用地址dto
     * @return:ServerResponse
     */
    ServerResponse delete(OfenLocationCommonDto ofenLocationCommonDto);
}
