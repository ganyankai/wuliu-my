package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.app.utils.WlQQVideoUrlUtils;
import com.zrytech.framework.base.annotation.CurrentUser;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.app.dto.WlArticleDto;
import com.zrytech.framework.common.entity.User;
import com.zrytech.framework.app.service.WlArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 11:26
 **/
@RestController
@RequestMapping("/api/article")
public class WlArticleController {

    @Autowired
    private WlArticleService articleService;

    
    /**
     * 解析视频播放地址
     * @param sourceUrl  视频网页播放地址
     * @return
     */
    @RequestMapping("/parseVideoSrc")
    public ServerResponse parseVideoSrc(@RequestBody RequestParams<WlArticleDto> param){
    	WlArticleDto wlArticleDto = param.getParams();
    	String sourceUrl = wlArticleDto.getSourceUrl();
    	if(StringUtils.isBlank(sourceUrl)) {
    		throw new BusinessException(112, "参数错误");
    	}
    	try {
    		String realUrl = WlQQVideoUrlUtils.getRealUrl(sourceUrl);
    		return ServerResponse.successWithData(realUrl);
		} catch (Exception e) {
			throw new BusinessException(112, "解析播放地址异常");
		}
    }
    
    
    /**
     * 文章列表
     * @param param
     * @return
     */
    @RequestMapping("/page")
    public ServerResponse findList(@RequestBody RequestParams<WlArticleDto> param){
        return articleService.selectArticlePage(param.getParams(),param.getPage());
    }

    /**
     * 添加文章
     * @param param
     * @return
     */
    @RequestMapping("/insert")
    public ServerResponse insert(@CurrentUser User user, @RequestBody RequestParams<WlArticleDto> param){
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
     * 修改文章
     * @param param
     * @return
     */
    @RequestMapping("/status")
    public ServerResponse status(@RequestBody RequestParams<WlArticleDto> param){
        return articleService.status(param.getParams());
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
