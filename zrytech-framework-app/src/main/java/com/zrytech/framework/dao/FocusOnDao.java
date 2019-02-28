package com.zrytech.framework.dao;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.entity.Focus;
import com.zrytech.framework.base.entity.Page;

import java.util.List;

public interface FocusOnDao {
    PageInfo<Focus> focusPage(Focus focus, Page page);

    Focus get(Integer id);

    int delete(Integer id);

    int add(Focus focus);

    List<Focus> selectCreateBy(Integer focuserId);

}
