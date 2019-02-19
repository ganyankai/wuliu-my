package com.zry.framework.dao;

import com.github.pagehelper.PageInfo;
import com.zry.framework.entity.FocusLine;
import com.zrytech.framework.base.entity.Page;

import java.util.List;

public interface FocusLineDao {
    PageInfo<FocusLine> linePage(FocusLine focusLine, Page page);

    FocusLine get(Integer id);

    int delete(Integer id);

    int addFocusLine(FocusLine focusLine);

    List<FocusLine> selectCreateBy(Integer focuserId);

}
