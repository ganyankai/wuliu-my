package com.zrytech.framework.app.service;

import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.app.dto.WlArticleCategoryDto;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 10:24
 **/
public interface WlArticleCategoryService {

    ServerResponse selectArticleCategoryList(WlArticleCategoryDto wlArticleCategoryDto);

}
