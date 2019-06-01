package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageInfo;
//import com.zrytech.framework.base.entity.Customer;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.BeanUtil;
import com.zrytech.framework.app.dao.WlArticleCommentDao;
import com.zrytech.framework.app.dao.WlArticleDao;
import com.zrytech.framework.common.dao.UserDao;
import com.zrytech.framework.app.dto.WlArticleCommentDto;
import com.zrytech.framework.app.entity.WlArticle;
import com.zrytech.framework.app.entity.WlArticleComment;
import com.zrytech.framework.common.entity.SysCustomer;
import com.zrytech.framework.common.entity.User;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import com.zrytech.framework.common.enums.UserType;
import com.zrytech.framework.app.service.WlArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 16:50
 **/
@Service
public class WlArticleCommentServiceImpl implements WlArticleCommentService {

    @Autowired
    private WlArticleCommentDao wlArticleCommentDao;

    @Autowired
    private WlArticleDao wlArticleDao;

    @Autowired
    private UserDao userDao;

    public ServerResponse insertArticleComment(WlArticleCommentDto wlArticleCommentDto, SysCustomer customer) {
        if (wlArticleCommentDto == null || wlArticleCommentDto.getArticleId() == null || customer == null || customer.getId() == 0) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        WlArticle wlArticle = wlArticleDao.selectArticleById(wlArticleCommentDto.getArticleId());
        if (wlArticle == null) {
            throw new BusinessException(new CommonResult(ResultEnum.REPOSITORY_NOT_EXIST));
        }
        WlArticleComment wlArticleComment = BeanUtil.copy(wlArticleCommentDto, WlArticleComment.class);
        wlArticleComment.setArticleCommentUserId(customer.getId());
//        articleComment.setCommentUserType(UserType.CUSTOMER.getInfo());
        wlArticleComment.setArticleCommentViewed(0);
        if (wlArticleComment.getArticleCommentParentId() == null) {
            wlArticleComment.setArticleCommentParentId(0);//给一级评论赋值;0:表示一级评论
        }
        wlArticleCommentDao.insertArticleComment(wlArticleComment);
        return ServerResponse.success();
    }

    public ServerResponse deleteArticleComment(WlArticleCommentDto wlArticleCommentDto) {
        if (wlArticleCommentDto == null || wlArticleCommentDto.getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        WlArticleComment wlArticleComment = BeanUtil.copy(wlArticleCommentDto, WlArticleComment.class);
        if (wlArticleCommentDto != null) {
            //TODO:删除二级评论以及子评论
            WlArticleComment deleteParent = new WlArticleComment();
            deleteParent.setArticleCommentParentId(wlArticleCommentDto.getId());
            List<Integer> listIds = wlArticleCommentDao.selectParentArticleIds(deleteParent);
            if (listIds != null && listIds.size() > 0) {
                //  System.out.println("集合:" + listIds);
                wlArticleCommentDao.deleteParentArticleIds(listIds);
            }
        }
        wlArticleCommentDao.deleteArticleComment(wlArticleComment);
        return ServerResponse.success();
    }

    public ServerResponse selectArticleCommentPage(WlArticleCommentDto wlArticleCommentDto, Page page) {
        if (wlArticleCommentDto == null || wlArticleCommentDto.getArticleId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        WlArticleComment wlArticleComment = BeanUtil.copy(wlArticleCommentDto, WlArticleComment.class);
        PageInfo<WlArticleComment> pageInfo = wlArticleCommentDao.selectArticleCommentPage(wlArticleComment, page);
        return ServerResponse.successWithData(pageInfo);
    }

   /* public void dealPageComment(PageInfo pageInfo) {
        List<ArticleComment> list = pageInfo.getList();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getArticleCommentParentId() != null) {
                    list.remove(i);
                    i--;
                }
            }
        }
    }*/

    public ServerResponse get(WlArticleCommentDto wlArticleCommentDto) {
        if (wlArticleCommentDto == null || wlArticleCommentDto.getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return ServerResponse.successWithData(wlArticleCommentDao.get(wlArticleCommentDto.getId()));
    }

    public ServerResponse saveArticleComment(WlArticleCommentDto wlArticleCommentDto, User user) {
        if (wlArticleCommentDto == null || wlArticleCommentDto.getArticleId() == null || user == null || user.getId() == 0) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        WlArticle wlArticle = wlArticleDao.selectArticleById(wlArticleCommentDto.getArticleId());
        if (wlArticle == null) {
            throw new BusinessException(new CommonResult(ResultEnum.REPOSITORY_NOT_EXIST));
        }
        WlArticleComment wlArticleComment = BeanUtil.copy(wlArticleCommentDto, WlArticleComment.class);
        wlArticleComment.setArticleCommentUserId(user.getId());
        wlArticleComment.setCommentUserType(UserType.ADMIN.getInfo());
        wlArticleComment.setArticleCommentViewed(0);
        wlArticleCommentDao.insertArticleComment(wlArticleComment);
        return ServerResponse.success();
    }

    public ServerResponse selectArticlePage(WlArticleCommentDto wlArticleCommentDto, Page page) {
        if (wlArticleCommentDto == null || wlArticleCommentDto.getArticleId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        WlArticleComment wlArticleComment = BeanUtil.copy(wlArticleCommentDto, WlArticleComment.class);
        WlArticle wlArticle = wlArticleDao.selectArticleById(wlArticleCommentDto.getArticleId());
        if (wlArticle != null && wlArticle.getArticleCategoryId() != null && wlArticle.getArticleCategoryId().intValue() == 1) {
            wlArticleComment.setArticleCommentParentId(0);
        }
        PageInfo<WlArticleComment> pageInfo = wlArticleCommentDao.selectArticleCommentPage(wlArticleComment, page);
        return ServerResponse.successWithData(pageInfo);
    }

    public ServerResponse selectReply(WlArticleCommentDto wlArticleCommentDto, Page page) {
        if (wlArticleCommentDto == null || wlArticleCommentDto.getArticleCommentUserId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        WlArticleComment wlArticleComment = BeanUtil.copy(wlArticleCommentDto, WlArticleComment.class);
        return ServerResponse.successWithData(wlArticleCommentDao.selectReply(wlArticleComment, page));
    }
}
