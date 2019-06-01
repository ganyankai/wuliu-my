package com.zrytech.framework.app.service;

//import com.zrytech.framework.base.entity.SysCustomer;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.app.dto.WlArticleLikeCollectDto;
import com.zrytech.framework.common.entity.SysCustomer;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 15:05
 **/
public interface WlArticleLikeCollectService {

    /**
     * 保存
     *
     * @param wlArticleLikeCollectDto
     * @return
     */
    ServerResponse save(WlArticleLikeCollectDto wlArticleLikeCollectDto, SysCustomer customer);

}
