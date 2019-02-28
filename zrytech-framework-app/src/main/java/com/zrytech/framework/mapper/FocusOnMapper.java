package com.zrytech.framework.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.entity.Focus;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FocusOnMapper {
    PageInfo<Focus> focusPage(@Param("focusOn") Focus focus, Page page);

    Focus get(@Param("id") Integer id);

    int delete(@Param("id") Integer id);

    int addFocus(Focus focus);

    int add(Focus focus);

    List<Focus> selectCreateBy(@Param("focuserId") Integer focuserId);
}
