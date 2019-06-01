package com.zrytech.framework.app.dao;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.app.entity.WlArticle;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 10:52
 **/
public interface WlArticleDao {

    /**
     * 添加文章
     * @param wlArticle
     * @return
     */
    int insertArticle(WlArticle wlArticle);

    /**
     * 删除文章
     * @param wlArticle
     * @return
     */
    int deleteArticle(WlArticle wlArticle);

    /**
     * 修改文章
     * @param wlArticle
     * @return
     */
    int updateArticle(WlArticle wlArticle);

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    WlArticle selectArticleById(Integer id);

    /**
     * 分页查询文章
     * @param wlArticle
     * @param page
     * @return
     */
    PageInfo selectArticlePage(WlArticle wlArticle, Page page);

}
