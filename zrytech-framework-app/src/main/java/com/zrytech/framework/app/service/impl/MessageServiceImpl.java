package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.MessageDao;
import com.zrytech.framework.app.dto.MessageDto;
import com.zrytech.framework.app.entity.Message;
import com.zrytech.framework.app.service.MessageService;
import com.zrytech.framework.app.utils.CheckFieldUtils;
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

    /**
     * Desintion:消息分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse messagePage(MessageDto messageDto, Page page) {
        Message message = BeanUtil.copy(messageDto, Message.class);
        PageInfo<Message> pageInfo = messageDao.messagePage(message, page);
        return ServerResponse.successWithData(pageInfo);
    }

    /**
     * Desintion:消息详情
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse get(MessageDto messageDto) {
        Message message = messageDao.get(messageDto.getId());
        if(message !=null){//查看消息详情;将消息改为已读状态
            int num=updateMsg(message.getId());
            CheckFieldUtils.assertSuccess(num);
        }
        return ServerResponse.successWithData(message);
    }

    /**
     * Desintion:消息添加
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse addMessage(MessageDto messageDto) {
        Message message = BeanUtil.copy(messageDto, Message.class);
        message.setSenderDate(new Date());
        message.setMarkRead(false);
        int num = messageDao.addMessage(message);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * Desintion:消息删除
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse delete(MessageDto messageDto) {
        int num = messageDao.delete(messageDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * Desintion:消息查看
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse selectMsgList(MessageDto messageDto) {
        Message message = BeanUtil.copy(messageDto, Message.class);
        List<Message> messageList = messageDao.selectMsgList(message);
        return ServerResponse.successWithData(messageList);
    }

    /**
     * Desintion:消息分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse selectTypePage(MessageDto messageDto, Page page) {
        Message message = BeanUtil.copy(messageDto, Message.class);
        PageInfo<Message> pageInfo = messageDao.messagePage(message, page);
        return ServerResponse.successWithData(pageInfo);
    }

    /**
     * @Deinition:jxx
     * @Author:Jxx
     * @param:消息ID
     * */
    public int updateMsg(Integer messageId){
        return  messageDao.updateMsg(messageId,true);
    }
}
