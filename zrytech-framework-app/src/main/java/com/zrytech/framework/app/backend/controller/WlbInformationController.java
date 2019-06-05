package com.zrytech.framework.app.backend.controller;

import com.zrytech.framework.base.annotation.CurrentUser;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.app.dto.WlArticleDto;
import com.zrytech.framework.common.entity.User;
import com.zrytech.framework.app.enums.WlArticleType;
import com.zrytech.framework.app.service.WlArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/07/26 16:29
 **/
@RestController
@RequestMapping("/admin/information")
public class WlbInformationController {

    @Autowired
    private WlArticleService articleService;


    /**
     * 文章列表
     * @param param
     * @return
     */
    @RequestMapping("/page")
    public ServerResponse findList(@RequestBody RequestParams<WlArticleDto> param){
        if(param.getParams()!=null) {
            param.getParams().setArticleCategoryId(WlArticleType.INFORMATION.getIndex());
        }else{
            WlArticleDto dto= new WlArticleDto();
            dto.setArticleCategoryId(WlArticleType.INFORMATION.getIndex());
            param.setParams(dto);
        }
        return articleService.selectArticlePage(param.getParams(),param.getPage());
    }

    /**
     * 添加文章
     * @param param
     * @return
     */
    @RequestMapping("/insert")
    public ServerResponse insert(@CurrentUser User user, @RequestBody RequestParams<WlArticleDto> param){
        if(param.getParams()!=null) {
            param.getParams().setArticleCategoryId(WlArticleType.INFORMATION.getIndex());
        }else{
            WlArticleDto dto= new WlArticleDto();
            dto.setArticleCategoryId(WlArticleType.INFORMATION.getIndex());
            param.setParams(dto);
        }
        return articleService.insertArticle(user,param.getParams());
    }

    /**
     * 删除文章
     * @param param
     * @return
     */
    @RequestMapping("/delete")
    public ServerResponse delete(@RequestBody RequestParams<WlArticleDto> param){
        return articleService.deleteArticle(param.getParams());
    }

    /**
     * 修改文章
     * @param param
     * @return
     */
    @RequestMapping("/update")
    public ServerResponse update(@RequestBody RequestParams<WlArticleDto> param){
        return articleService.updateArticle(param.getParams());
    }

    /**
     * 根据id查文章
     * @param param
     * @return
     */
    @RequestMapping("/articlebyid")
    public ServerResponse articleById(@RequestBody RequestParams<WlArticleDto> param){
        return articleService.selectArticleById(param.getParams());
    }

}
