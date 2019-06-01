package com.zrytech.framework.app.service;

import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.app.dto.WlArticleDto;
import com.zrytech.framework.common.entity.User;

/**
 * ${DESCRIPTION}
 * @author zhanhao
 * @date 2018/05/04 10:58
 **/
public interface WlArticleService {

    ServerResponse insertArticle(User user, WlArticleDto wlArticleDto);

    ServerResponse deleteArticle(WlArticleDto wlArticleDto);

    ServerResponse updateArticle(WlArticleDto wlArticleDto);

    ServerResponse selectArticleById(WlArticleDto articleDt);

    ServerResponse selectArticlePage(WlArticleDto wlArticleDto, Page page);

    ServerResponse status(WlArticleDto params);

}
