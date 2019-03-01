package com.zrytech.framework.app.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.FocusOnDao;
import com.zrytech.framework.app.entity.Focus;
import com.zrytech.framework.app.mapper.FocusOnMapper;
import com.zrytech.framework.base.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class FocusOnDaoImpl implements FocusOnDao {

    @Autowired
    private FocusOnMapper focusOnMapper;

    @Override
    public PageInfo<Focus> focusPage(Focus focus, Page page) {
        if(page==null){
             page=new Page();
        }
        return focusOnMapper.focusPage(focus,page);
    }

    @Override
    public Focus get(Integer id) {
        return focusOnMapper.get(id);
    }

    @Override
    public int delete(Integer id) {
        return focusOnMapper.delete(id);
    }

    @Override
    public int add(Focus focus) {
        return focusOnMapper.add(focus);
    }

    @Override
    public List<Focus> selectCreateBy(Integer focuserId) {
        return focusOnMapper.selectCreateBy(focuserId);
    }
}
