package com.zrytech.framework.app.dao;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.Waybill;
import com.zrytech.framework.base.entity.Page;

import java.util.List;

public interface WaybillDao {

    int createIndent(Waybill waybill);

    int updateIndentStatus(Waybill waybill);

    PageInfo<Waybill> indentPage(Waybill waybill, Page page);

    List<String> coundIndent(Integer cargoOwnnerId);

    Waybill get(Integer id);

    int changeIndent(Waybill waybill);

    int delete(Integer id);

}
