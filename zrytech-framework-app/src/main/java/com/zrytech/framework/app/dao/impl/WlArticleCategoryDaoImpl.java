package com.zrytech.framework.app.dao.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.WlArticleCategoryDao;
import com.zrytech.framework.app.entity.WlArticleCategory;
import com.zrytech.framework.app.mapper.WlArticleCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 10:06
 **/
@Repository
public class WlArticleCategoryDaoImpl implements WlArticleCategoryDao {

    @Autowired
    private WlArticleCategoryMapper wlArticleCategoryMapper;

    @Override
    public int insertArticleCategory(WlArticleCategory wlArticleCategory) {
        return wlArticleCategoryMapper.insertArticleCategory(wlArticleCategory);
    }

    @Override
    public int deleteArticleCategory(WlArticleCategory wlArticleCategory) {
        return wlArticleCategoryMapper.deleteArticleCategory(wlArticleCategory);
    }

    @Override
    public int updateArticleCategory(WlArticleCategory wlArticleCategory) {
        return wlArticleCategoryMapper.updateArticleCategory(wlArticleCategory);
    }

    @Override
    public WlArticleCategory selectArticleCategoryById(Integer id) {
        return wlArticleCategoryMapper.selectArticleCategoryById( id );
    }

    @Override
    public List<WlArticleCategory> selectArticleCategoryList(WlArticleCategory wlArticleCategory) {
        return wlArticleCategoryMapper.selectArticleCategoryList(wlArticleCategory);
    }

    @Override
    public PageInfo selectArticleCategoryPage(WlArticleCategory wlArticleCategory, Page page) {
        return wlArticleCategoryMapper.selectArticleCategoryPage(wlArticleCategory,page );
    }
}
