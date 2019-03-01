package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.FocusLineDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

public interface FocusLineService {
    /**
     * Desintion:路线分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:FocusLineDto路线dto
     * @return:ServerResponse
     */
    ServerResponse linePage(FocusLineDto focusLineDto, Page page);

    /**
     * Desintion:路线详情
     *
     * @author:jiangxiaoxiang
     * @param:FocusLineDto路线dto
     * @return:ServerResponse
     */
    ServerResponse get(FocusLineDto focusLineDto);

    /**
     * Desintion:删除路线
     *
     * @author:jiangxiaoxiang
     * @param:FocusLineDto路线dto
     * @return:ServerResponse
     */
    ServerResponse delete(FocusLineDto focusLineDto);

    /**
     * Desintion:添加路线
     *
     * @author:jiangxiaoxiang
     * @param:FocusLineDto路线dto
     * @return:ServerResponse
     */
    ServerResponse add(FocusLineDto focusLineDto);
}
