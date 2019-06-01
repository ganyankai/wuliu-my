package com.zrytech.framework.app.front.controller;


import com.zrytech.framework.app.enums.WlArticleType;
import com.zrytech.framework.app.service.WlArticleService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.app.dto.WlArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:公告
 * @author: jxx
 * @create: 2018/08/21 16:56
 */
@RestController
@RequestMapping("/api/notice")
public class WlNoticeFontController {

    @Autowired
    private WlArticleService articleService;

    /**
     * @Desinition:文章列表
     * @param:ArticleDto
     * @param:page
     * @return:ServerResponse
     */
    @RequestMapping("/noticePage")
    public ServerResponse findList(@RequestBody RequestParams<WlArticleDto> param) {
        if (param.getParams() != null) {
            param.getParams().setArticleStatus(1);
            param.getParams().setArticleCategoryId(WlArticleType.NOTICE.getIndex());
        } else {
            WlArticleDto dto = new WlArticleDto();
            dto.setArticleCategoryId(WlArticleType.NOTICE.getIndex());
            dto.setArticleStatus(1);
            param.setParams(dto);
        }
        return articleService.selectArticlePage(param.getParams(), param.getPage());
    }

    /**
     * @Desinition:根据id查文章
     * @param:ArticleDto
     * @return:ServerResponse
     */
    @RequestMapping("/get")
    public ServerResponse articleById(@RequestBody RequestParams<WlArticleDto> param) {
        return articleService.selectArticleById(param.getParams());
    }

}
