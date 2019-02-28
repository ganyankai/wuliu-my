package com.zrytech.framework.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.entity.Message;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    PageInfo<Message> messagePage(@Param("message") Message message, Page page);

    Message get(@Param("id") Integer id);

    int addMessage(Message message);

    int delete(@Param("id") Integer id);

    List<Message> selectMsgList(Message message);

    int updateMsg(@Param("messageId") Integer messageId,@Param("isRead") boolean b);
}
