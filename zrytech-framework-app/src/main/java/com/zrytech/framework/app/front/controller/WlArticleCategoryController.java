package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.app.dto.WlArticleCategoryDto;
import com.zrytech.framework.app.service.WlArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 10:31
 **/
@RestController
@RequestMapping("/api/articlecategory")
public class WlArticleCategoryController {

    @Autowired
    private WlArticleCategoryService wlArticleCategoryService;

    /**
     * 文章分类列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    public ServerResponse findList(@RequestBody RequestParams<WlArticleCategoryDto> params){
        return wlArticleCategoryService.selectArticleCategoryList(params.getParams());
    }

}
