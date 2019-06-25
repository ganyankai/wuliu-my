package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.MessageDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

import java.util.List;

public interface MessageService {
    /**
     * 一键已读
     * @param id
     * @param list
     * @return
     */
    ServerResponse markRead(Integer id,List<Integer> list);

    /**
     * 统计不同分类下未读消息
     * @param id
     * @return
     */
    ServerResponse accessMessageCount(Integer id);

    /**
     * Desintion:消息分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    ServerResponse messagePage(MessageDto messageDto, Page page);

    /**
     * Desintion:消息详情
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    ServerResponse get(MessageDto messageDto);

    /**
     * Desintion:消息添加
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    ServerResponse addMessage(MessageDto messageDto);

    /**
     * Desintion:消息删除
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    ServerResponse delete(MessageDto messageDto);

    /**
     * Desintion:消息查看
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    ServerResponse selectMsgList(MessageDto messageDto);

    /**
     * Desintion:消息分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    ServerResponse selectTypePage(MessageDto messageDto, Page page);
}
