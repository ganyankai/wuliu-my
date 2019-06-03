package com.zrytech.framework.app.front.controller;


import com.zrytech.framework.base.annotation.CurrentCustomer;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.app.dto.WlArticleCommentDto;
import com.zrytech.framework.common.entity.SysCustomer;
import com.zrytech.framework.app.service.WlArticleCommentService;
//import com.zrytech.framework.price.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desinition:文章评论
 * @author:jxx
 * @date:2018-08-21
 * */
@RestController
@RequestMapping("/api/articleComment")
public class WlCommentsController {

    @Autowired
    private WlArticleCommentService articleCommentService;

    /**
     * 添加文章评论
     * @param params
     * @return
     */
    @RequestMapping("/addArticle")
    public ServerResponse save(@RequestBody RequestParams<WlArticleCommentDto> params, @CurrentCustomer SysCustomer customer){
        return articleCommentService.insertArticleComment( params.getParams(),customer);
    }

    /**
     * 评论列表
     * @param params
     * @return
     */
    @RequestMapping("/articlePage")
    public ServerResponse findList(@RequestBody RequestParams<WlArticleCommentDto> params){
        return articleCommentService.selectArticleCommentPage(params.getParams(),params.getPage());
    }

    /**
     * 查询回复列表
     * @param params
     * @return
     */
    @RequestMapping("/rePlypage")
    public ServerResponse reply(@RequestBody RequestParams<WlArticleCommentDto> params){
        return articleCommentService.selectReply(params.getParams(),params.getPage());
    }

    /**
     * 查询某个评论
     * @param params
     * @return
     */
    @RequestMapping("/getId")
    public ServerResponse get(@RequestBody RequestParams<WlArticleCommentDto> params){
        return articleCommentService.get(params.getParams());
    }
}
