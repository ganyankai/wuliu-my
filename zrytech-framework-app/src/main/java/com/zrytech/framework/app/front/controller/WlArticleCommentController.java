package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.base.annotation.CurrentUser;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.app.dto.WlArticleCommentDto;
import com.zrytech.framework.common.entity.User;
import com.zrytech.framework.app.service.WlArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 17:02
 **/
@RestController
@RequestMapping("/api/articleComment")
public class WlArticleCommentController {

    @Autowired
    private WlArticleCommentService wlArticleCommentService;

    /**
     * 添加文章评论
     * @param params
     * @return
     */
    @RequestMapping("/save")
    public ServerResponse save(@RequestBody RequestParams<WlArticleCommentDto> params, @CurrentUser User user){
        return wlArticleCommentService.saveArticleComment( params.getParams(),user);
    }

    /**
     * 删除文章评论
     * @param params
     * @return
     */
    @RequestMapping("/delete")
    public ServerResponse delete(@RequestBody RequestParams<WlArticleCommentDto> params){
        return wlArticleCommentService.deleteArticleComment( params.getParams() );
    }

    /**
     * 评论列表
     * @param params
     * @return
     */
    @RequestMapping("/page")
    public ServerResponse findList(@RequestBody RequestParams<WlArticleCommentDto> params){
        return wlArticleCommentService.selectArticlePage(params.getParams(),params.getPage());
    }

    /**
     * 查询回复列表
     * @param params
     * @return
     */
    @RequestMapping("/replypage")
    public ServerResponse reply(@RequestBody RequestParams<WlArticleCommentDto> params){
        return wlArticleCommentService.selectReply(params.getParams(),params.getPage());
    }

    /**
     * 查询某个评论
     * @param params
     * @return
     */
    @RequestMapping("/get")
    public ServerResponse get(@RequestBody RequestParams<WlArticleCommentDto> params){
        return wlArticleCommentService.get(params.getParams());
    }

}
