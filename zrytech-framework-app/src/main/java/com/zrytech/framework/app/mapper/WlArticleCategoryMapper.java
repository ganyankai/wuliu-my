package com.zrytech.framework.app.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.WlArticleCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: zrytech-framework-parent
 * @description:
 * @author: LiuChao
 * @create: 2018-05-03 14:40
 **/
public interface WlArticleCategoryMapper {

    int insertArticleCategory(WlArticleCategory wlArticleCategory);

    int deleteArticleCategory(WlArticleCategory wlArticleCategory);
    
    int updateArticleCategory(WlArticleCategory wlArticleCategory);

    WlArticleCategory selectArticleCategoryById(@Param("id") Integer id);

    List<WlArticleCategory> selectArticleCategoryList(WlArticleCategory wlArticleCategory);
    
    PageInfo selectArticleCategoryPage(@Param("articleCategory") WlArticleCategory wlArticleCategory, Page page);
}