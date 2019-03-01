package com.zrytech.framework.app.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.FocusLineDao;
import com.zrytech.framework.app.entity.FocusLine;
import com.zrytech.framework.app.mapper.FocusLineMapper;
import com.zrytech.framework.base.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class FocusLineDaoImpl implements FocusLineDao {

    @Autowired
    private FocusLineMapper focusLineMapper;

    @Override
    public PageInfo<FocusLine> linePage(FocusLine focusLine, Page page) {
        if(page==null){
           page=new Page();
        }
        return focusLineMapper.linePage(focusLine,page);
    }

    @Override
    public FocusLine get(Integer id) {
        return focusLineMapper.get(id);
    }

    @Override
    public int delete(Integer id) {
        return focusLineMapper.delete(id);
    }

    @Override
    public int addFocusLine(FocusLine focusLine) {
        return focusLineMapper.addFocusLine(focusLine);
    }

    @Override
    public List<FocusLine> selectCreateBy(Integer focuserId) {
        return focusLineMapper.selectCreateBy(focuserId);
    }
}
