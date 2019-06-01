package com.zrytech.framework.app.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.app.dao.WlArticleCommentDao;
import com.zrytech.framework.app.entity.WlArticleComment;
import com.zrytech.framework.app.mapper.WlArticleCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 16:44
 **/
@Repository
public class WlArticleCommentDtoImpl implements WlArticleCommentDao {

    @Autowired
    private WlArticleCommentMapper wlArticleCommentMapper;

    public void insertArticleComment(WlArticleComment wlArticleComment) {
        wlArticleCommentMapper.insertArticleComment(wlArticleComment);
    }

    public void deleteArticleComment(WlArticleComment wlArticleComment) {
        wlArticleCommentMapper.deleteArticleComment(wlArticleComment);
    }

    public void updateArticleComment(WlArticleComment wlArticleComment) {
        wlArticleCommentMapper.updateArticleComment(wlArticleComment);
    }

    public WlArticleComment selectArticleCommentById(Long id) {
        return wlArticleCommentMapper.selectArticleCommentById( id );
    }

    public List<WlArticleComment> selectArticleCommentList(WlArticleComment wlArticleComment) {
        return wlArticleCommentMapper.selectArticleCommentList(wlArticleComment);
    }

    public PageInfo<WlArticleComment> selectArticleCommentPage(WlArticleComment wlArticleComment, Page page) {
        return wlArticleCommentMapper.selectArticleCommentPage(wlArticleComment,page );
    }

    public List<WlArticleComment> selectReply(WlArticleComment wlArticleComment, Page page) {
        return wlArticleCommentMapper.selectReply(wlArticleComment,page);
    }

    @Override
    public WlArticleComment get(Integer id) {
        return wlArticleCommentMapper.selectArticleCommentById( id);
    }

    public List<Integer> selectParentArticleIds(WlArticleComment deleteParent) {
        return wlArticleCommentMapper.selectParentArticleIds(deleteParent);
    }

    @Override
    public void deleteParentArticleIds(List<Integer> listIds) {
        wlArticleCommentMapper.deleteParentArticleIds(listIds);
    }


}
