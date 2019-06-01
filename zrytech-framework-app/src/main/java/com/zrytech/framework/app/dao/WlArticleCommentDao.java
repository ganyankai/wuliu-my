package com.zrytech.framework.app.dao;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.app.entity.WlArticleComment;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 16:45
 **/
public interface WlArticleCommentDao {

    void insertArticleComment(WlArticleComment wlArticleComment);

    void deleteArticleComment(WlArticleComment wlArticleComment);

    void updateArticleComment(WlArticleComment wlArticleComment);

    WlArticleComment selectArticleCommentById(Long id);

    List<WlArticleComment> selectArticleCommentList(WlArticleComment wlArticleComment);

    PageInfo<WlArticleComment> selectArticleCommentPage(WlArticleComment wlArticleComment, Page page);

    List<WlArticleComment> selectReply(WlArticleComment wlArticleComment, Page page);

    WlArticleComment get(Integer id);

    List<Integer> selectParentArticleIds(WlArticleComment deleteParent);

    void deleteParentArticleIds(List<Integer> listIds);

}
