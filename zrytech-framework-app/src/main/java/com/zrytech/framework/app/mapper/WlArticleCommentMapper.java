package com.zrytech.framework.app.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.app.entity.WlArticleComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: zrytech-framework-parent
 * @description:
 * @author: LiuChao
 * @create: 2018-05-03 14:40
 **/
public interface WlArticleCommentMapper {

    int insertArticleComment(WlArticleComment wlArticleComment);

    int deleteArticleComment(WlArticleComment wlArticleComment);

    int updateArticleComment(WlArticleComment wlArticleComment);

    WlArticleComment selectArticleCommentById(@Param("id") Long id);

    List<WlArticleComment> selectArticleCommentList(WlArticleComment wlArticleComment);

    PageInfo<WlArticleComment> selectArticleCommentPage(@Param("articleComment") WlArticleComment wlArticleComment, Page page);

    List<WlArticleComment> selectReply(@Param("articleComment") WlArticleComment wlArticleComment, Page page);

    WlArticleComment selectArticleCommentById(Integer id);

    List<Integer> selectParentArticleIds(WlArticleComment deleteParent);

    void deleteParentArticleIds(@Param("list") List<Integer> listIds);
}