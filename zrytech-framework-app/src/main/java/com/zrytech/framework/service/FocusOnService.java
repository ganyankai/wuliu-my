package com.zrytech.framework.service;

import com.zrytech.framework.dto.FocusDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

public interface FocusOnService {
    /**
     * Desintion:关注分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    ServerResponse focusPage(FocusDto focusDto, Page page);

    /**
     * Desintion:关注详情
     *
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    ServerResponse get(FocusDto focusDto);

    /**
     * Desintion:删除关注
     *
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    ServerResponse delete(FocusDto focusDto);

    /**
     * Desintion:添加关注
     *
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    ServerResponse add(FocusDto focusDto);

    /**
     * Desintion:我的关注
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    ServerResponse selectMyFocus(FocusDto focusDto);
}
