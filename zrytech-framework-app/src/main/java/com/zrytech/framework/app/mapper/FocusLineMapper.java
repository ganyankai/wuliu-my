package com.zrytech.framework.app.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.FocusLine;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FocusLineMapper {
    PageInfo<FocusLine> linePage(@Param("focusLine") FocusLine focusLine, Page page);

    FocusLine get(@Param("id") Integer id);

    int delete(@Param("id") Integer id);

    int addFocusLine(FocusLine focusLine);

    List<FocusLine> selectCreateBy(@Param("focuserId") Integer focuserId);
}
