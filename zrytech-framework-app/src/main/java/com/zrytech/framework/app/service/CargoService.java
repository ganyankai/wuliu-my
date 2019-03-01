package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.CargoDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.entity.User;

public interface CargoService {
    /**
     * Desintion:货源分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse cargoPage(CargoDto cargoDto, Page page);

    /**
     * Desintion:货源详情
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse get(CargoDto cargoDto);

    /**
     * Desintion:货源审核
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse auditSource(CargoDto cargoDto,User user);

    /**
     * Desintion:发布货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse pushResource(CargoDto cargoDto);

    /**
     * Desintion:修改货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse updateSource(CargoDto cargoDto);

    /**
     * Desintion:删除货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse deleteSource(CargoDto cargoDto);

    /**
     * Desintion:邀请报价(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse invitationOffer(CargoDto cargoDto);

    /**
     * Desintion:我的货源分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse mySourcePage(CargoDto cargoDto, Page page);

    /**
     * Desintion:取消发布货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse cancelResource(CargoDto cargoDto);
}
