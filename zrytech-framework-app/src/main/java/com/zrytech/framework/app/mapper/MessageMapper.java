package com.zrytech.framework.app.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.Message;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MessageMapper {
    /**
     * 一键已读
     * @param id 用户id
     * @param idsStr (1,2,3)
     * @return
     */
    @Update("update sys_message set mark_read = 1,read_date = now() where reveicer_id = #{id} and id IN ${idsStr}")
    public Integer markRead(@Param("id") Integer id , @Param("idsStr") String idsStr);

    // 统计所有未读消息
    @Select("select count(id) from sys_message where mark_read = 0 and reveicer_id = #{id}")
    Integer accessAllNotRead(@Param("id") Integer id);

    //审核分类下未读
    @Select("select count(id) from sys_message where mark_read = 0 and msg_type = 'approving_message' and reveicer_id = #{id}")
    Integer accessApprovingNotRead(@Param("id") Integer id);

    //竞价分类下未读
    @Select("select count(id) from sys_message where mark_read = 0 and msg_type = 'bidding_message' and reveicer_id = #{id}")
    Integer accessBiddingNotRead(@Param("id") Integer id);

    //运单分类下未读
    @Select("select count(id) from sys_message where mark_read = 0 and msg_type = 'waybill_message' and reveicer_id = #{id}")
    Integer accessWaybillNotRead(@Param("id") Integer id);

    PageInfo<Message> messagePage(@Param("message") Message message, Page page);

    Message get(@Param("id") Integer id);

    int addMessage(Message message);

    int delete(@Param("id") Integer id);

    List<Message> selectMsgList(Message message);

    int updateMsg(@Param("messageId") Integer messageId,@Param("isRead") boolean b);

}
