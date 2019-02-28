package com.zrytech.framework.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.dao.MessageDao;
import com.zrytech.framework.entity.Message;
import com.zrytech.framework.mapper.MessageMapper;
import com.zrytech.framework.base.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class MessageDaoImpl implements MessageDao {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public PageInfo<Message> messagePage(Message message, Page page) {
        if(page==null){
            page=new Page();
        }
        return messageMapper.messagePage(message,page);
    }

    @Override
    public Message get(Integer id) {
        return messageMapper.get(id);
    }

    @Override
    public int addMessage(Message message) {
        return messageMapper.addMessage(message);
    }

    @Override
    public int delete(Integer id) {
        return messageMapper.delete(id);
    }

    @Override
    public List<Message> selectMsgList(Message message) {
        return messageMapper.selectMsgList(message);
    }

    @Override
    public int updateMsg(Integer messageId, boolean b) {
        return messageMapper.updateMsg(messageId,b);
    }
}
