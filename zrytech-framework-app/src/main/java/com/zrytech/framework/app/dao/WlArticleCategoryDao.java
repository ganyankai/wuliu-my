package com.zrytech.framework.app.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.WlArticleCategory;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 10:06
 **/
public interface WlArticleCategoryDao {

    int insertArticleCategory(WlArticleCategory wlArticleCategory);

    int deleteArticleCategory(WlArticleCategory wlArticleCategory);

    int updateArticleCategory(WlArticleCategory wlArticleCategory);

    WlArticleCategory selectArticleCategoryById(Integer id);

    List<WlArticleCategory> selectArticleCategoryList(WlArticleCategory wlArticleCategory);

    PageInfo selectArticleCategoryPage(WlArticleCategory wlArticleCategory, Page page);

}
