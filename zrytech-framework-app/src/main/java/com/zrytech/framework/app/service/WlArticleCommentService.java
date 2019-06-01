package com.zrytech.framework.app.service;

//import com.zrytech.framework.base.entity.Customer;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.app.dto.WlArticleCommentDto;
import com.zrytech.framework.common.entity.SysCustomer;
import com.zrytech.framework.common.entity.User;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 16:49
 **/
public interface WlArticleCommentService {

    ServerResponse insertArticleComment(WlArticleCommentDto wlArticleCommentDto, SysCustomer customer);

    ServerResponse deleteArticleComment(WlArticleCommentDto wlArticleCommentDto);

    ServerResponse selectReply(WlArticleCommentDto wlArticleCommentDto, Page page);

    ServerResponse selectArticleCommentPage(WlArticleCommentDto wlArticleCommentDto, Page page);

    ServerResponse get(WlArticleCommentDto params);

    ServerResponse saveArticleComment(WlArticleCommentDto params, User user);

    ServerResponse selectArticlePage(WlArticleCommentDto wlArticleCommentDto, Page page);
}
