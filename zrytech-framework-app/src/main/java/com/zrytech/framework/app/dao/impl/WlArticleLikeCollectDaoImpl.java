package com.zrytech.framework.app.dao.impl;

import com.zrytech.framework.app.dao.WlArticleLikeCollectDao;
import com.zrytech.framework.app.entity.WlArticleLikeCollect;
import com.zrytech.framework.app.mapper.WlArticleLikeCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 14:57
 **/
@Repository
public class WlArticleLikeCollectDaoImpl implements WlArticleLikeCollectDao {

    @Autowired
    private WlArticleLikeCollectMapper wlArticleLikeCollectMapper;

    @Override
    public void insertArticleLikeCollect(WlArticleLikeCollect sysWlArticleLikeCollect) {
        wlArticleLikeCollectMapper.insertArticleLikeCollect(sysWlArticleLikeCollect);
    }

    @Override
    public void updateArticleLikeCollect(WlArticleLikeCollect sysWlArticleLikeCollect) {
        wlArticleLikeCollectMapper.updateArticleLikeCollect(sysWlArticleLikeCollect);
    }

    @Override
    public WlArticleLikeCollect queryObject(Integer id) {
        return wlArticleLikeCollectMapper.queryObject( id );
    }

    @Override
    public void delete(Integer articleId) {
        wlArticleLikeCollectMapper.delete( articleId );
    }
}
