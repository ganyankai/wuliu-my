package com.zrytech.framework.app.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.app.dao.WlArticleDao;
import com.zrytech.framework.app.entity.WlArticle;
import com.zrytech.framework.app.mapper.WlArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 10:53
 **/
@Repository
public class WlArticleDaoImpl implements WlArticleDao {

    @Autowired
    private WlArticleMapper wlArticleMapper;

    @Override
    public int insertArticle(WlArticle wlArticle) {
        return wlArticleMapper.insertArticle(wlArticle);
    }

    @Override
    public int deleteArticle(WlArticle wlArticle) {
        return wlArticleMapper.deleteArticle(wlArticle);
    }

    @Override
    public int updateArticle(WlArticle wlArticle) {
        return wlArticleMapper.updateArticle(wlArticle);
    }

    @Override
    public WlArticle selectArticleById(Integer id) {
        return wlArticleMapper.selectArticleById( id );
    }

    @Override
    public PageInfo selectArticlePage(WlArticle wlArticle, Page page) {
        return wlArticleMapper.selectArticlePage(wlArticle,page );
    }
}
