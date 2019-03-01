package com.zrytech.framework.app.dao;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.Message;
import com.zrytech.framework.base.entity.Page;

import java.util.List;

public interface MessageDao {
    PageInfo<Message> messagePage(Message message, Page page);

    Message get(Integer id);

    int addMessage(Message message);

    int delete(Integer id);

    List<Message> selectMsgList(Message message);

    int updateMsg(Integer messageId, boolean b);

}
