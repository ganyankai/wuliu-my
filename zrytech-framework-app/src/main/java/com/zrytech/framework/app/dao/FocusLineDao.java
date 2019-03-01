package com.zrytech.framework.app.dao;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.FocusLine;
import com.zrytech.framework.base.entity.Page;

import java.util.List;

public interface FocusLineDao {
    PageInfo<FocusLine> linePage(FocusLine focusLine, Page page);

    FocusLine get(Integer id);

    int delete(Integer id);

    int addFocusLine(FocusLine focusLine);

    List<FocusLine> selectCreateBy(Integer focuserId);

}
