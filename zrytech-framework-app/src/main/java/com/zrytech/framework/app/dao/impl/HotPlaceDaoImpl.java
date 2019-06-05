package com.zrytech.framework.app.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.HotPlaceDao;
import com.zrytech.framework.app.entity.HotPlace;
import com.zrytech.framework.app.mapper.HotPlaceMapper;
import com.zrytech.framework.base.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HotPlaceDaoImpl implements HotPlaceDao {
    
    @Autowired
    private HotPlaceMapper hotPlaceMapper;

    public PageInfo<HotPlace> hotPlacePage(HotPlace hotPlace, Page page) {
        if(page==null){
            page=new Page();
        }
        return hotPlaceMapper.hotPlacePage(hotPlace,page);
    }

    @Override
    public HotPlace get(Integer id) {
        return hotPlaceMapper.get(id);
    }

    @Override
    public int update(HotPlace hotPlace) {
        return hotPlaceMapper.update(hotPlace);
    }

    @Override
    public int delete(Integer id) {
        return hotPlaceMapper.delete(id);
    }
}
