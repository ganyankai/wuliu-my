package com.zrytech.framework.app.mapper;

import com.zrytech.framework.app.entity.WlArticleLikeCollect;
import org.apache.ibatis.annotations.Param;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 14:41
 **/
public interface WlArticleLikeCollectMapper {

    int insertArticleLikeCollect(WlArticleLikeCollect sysWlArticleLikeCollect);

    int updateArticleLikeCollect(WlArticleLikeCollect sysWlArticleLikeCollect);

    WlArticleLikeCollect queryObject(@Param("id") Integer id);

    int delete(@Param("articleId") Integer articleId);

}
