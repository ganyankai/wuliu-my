package com.zry.framework.service.impl;

import com.github.pagehelper.PageInfo;
import com.zry.framework.dao.MessageDao;
import com.zry.framework.dto.MessageDto;
import com.zry.framework.entity.Message;
import com.zry.framework.service.MessageService;
import com.zry.framework.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public ServerResponse messagePage(MessageDto messageDto, Page page) {
        Message message = BeanUtil.copy(messageDto, Message.class);
        PageInfo<Message> pageInfo = messageDao.messagePage(message, page);
        return ServerResponse.successWithData(pageInfo);
    }

    @Override
    public ServerResponse get(MessageDto messageDto) {
        Message message = messageDao.get(messageDto.getId());
        return ServerResponse.successWithData(message);
    }

    @Override
    public ServerResponse addMessage(MessageDto messageDto) {
        Message message = BeanUtil.copy(messageDto, Message.class);
        message.setSenderDate(new Date());
        int num = messageDao.addMessage(message);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse delete(MessageDto messageDto) {
        int num = messageDao.delete(messageDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse selectMsgList(MessageDto messageDto) {
        Message message = BeanUtil.copy(messageDto, Message.class);
        List<Message> messageList = messageDao.selectMsgList(message);
        return ServerResponse.successWithData(messageList);
    }

    @Override
    public ServerResponse selectTypePage(MessageDto messageDto, Page page) {
        Message message = BeanUtil.copy(messageDto, Message.class);
        PageInfo<Message> pageInfo = messageDao.messagePage(message, page);
        return ServerResponse.successWithData(pageInfo);
    }

    public int updateMsg(Integer messageId){
        return  messageDao.updateMsg(messageId,true);
    }
}
