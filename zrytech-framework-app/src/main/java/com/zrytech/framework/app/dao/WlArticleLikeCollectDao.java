package com.zrytech.framework.app.dao;

import com.zrytech.framework.app.entity.WlArticleLikeCollect;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 14:54
 **/
public interface WlArticleLikeCollectDao {

    /**
     * 添加点赞收藏
     *
     * @param sysWlArticleLikeCollect
     * @return
     */
    void insertArticleLikeCollect(WlArticleLikeCollect sysWlArticleLikeCollect);

    /**
     * 修改点赞收藏
     *
     * @param sysWlArticleLikeCollect
     * @return
     */
    void updateArticleLikeCollect(WlArticleLikeCollect sysWlArticleLikeCollect);

    /**
     * 根据id查询点赞收藏
     *
     * @param id
     * @return
     */
    WlArticleLikeCollect queryObject(Integer id);

    /**
     * 根据文章id删除记录
     *
     * @param articleId
     * @return
     */
    void delete(Integer articleId);

}
