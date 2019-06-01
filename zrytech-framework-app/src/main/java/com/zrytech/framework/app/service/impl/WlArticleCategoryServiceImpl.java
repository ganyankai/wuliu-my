package com.zrytech.framework.app.service.impl;

import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.BeanUtil;
import com.zrytech.framework.app.dao.WlArticleCategoryDao;
import com.zrytech.framework.app.dto.WlArticleCategoryDto;
import com.zrytech.framework.app.entity.WlArticleCategory;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import com.zrytech.framework.app.service.WlArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 10:25
 **/
@Service
public class WlArticleCategoryServiceImpl implements WlArticleCategoryService {

    @Autowired
    private WlArticleCategoryDao articleCategoryDao;

    public ServerResponse selectArticleCategoryList(WlArticleCategoryDto articleCategoryDto) {
        if(articleCategoryDto==null){
            throw new BusinessException( new CommonResult( ResultEnum.OBJECT_ERROR ) );
        }
        WlArticleCategory articleCategory= BeanUtil.copy(articleCategoryDto,WlArticleCategory.class);
        return ServerResponse.successWithData( articleCategoryDao.selectArticleCategoryList( articleCategory ) );
    }
}
