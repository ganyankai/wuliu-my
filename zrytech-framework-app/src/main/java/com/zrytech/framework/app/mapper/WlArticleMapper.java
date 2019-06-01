package com.zrytech.framework.app.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.app.entity.WlArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: zrytech-framework-parent
 * @description:
 * @author: LiuChao
 * @create: 2018-05-03 14:40
 **/
public interface WlArticleMapper {

    int insertArticle(WlArticle wlArticle);

    int deleteArticle(WlArticle wlArticle);
    
    int updateArticle(WlArticle wlArticle);

    WlArticle selectArticleById(@Param("id") Integer id);

    List<WlArticle> selectArticleList(WlArticle wlArticle);
    
    PageInfo selectArticlePage(@Param("article") WlArticle wlArticle, Page page);
}