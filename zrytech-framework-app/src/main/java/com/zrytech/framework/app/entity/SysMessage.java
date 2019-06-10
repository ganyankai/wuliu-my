package com.zrytech.framework.app.entity;

import com.zrytech.framework.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@ApiModel(value = "消息表")
@Table(name = "`sys_message`")
public class SysMessage extends BaseEntity {
    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 消息类型 */
    @Column(name = "`msg_type`")
    private String msgType;

    /** 发送人 */
    @Column(name = "`sender_id`")
    private Integer senderId;

    /** 发送人类型  车主, 货主,司机, 系统*/
    @Column(name = "`sender_type`")
    private String senderType;

    /** 发送日期 */
    @Column(name = "`sender_date`")
    private Date senderDate;



    /** 内容 */
    @Column(name = "`content`")
    private String content;

    /** 接收人 */
    @Column(name = "`reveicer_id`")
    private Integer reveicerId;

    /** 接收人类型：车主, 货主,司机, 系统 */
    @Column(name = "`reveicer_type`")
    private String reveicerType;

    /** 已读*/
    @Column(name = "`mark_read`")
    private Integer markRead;


    /** 查看日期*/
    @Column(name = "`read_date`")
    private Date readDate;

    public SysMessage(String msgType, Integer senderId, String senderType, Date senderDate, String content, Integer reveicerId, String reveicerType, Integer markRead, Date readDate) {
        this.msgType = msgType;
        this.senderId = senderId;
        this.senderType = senderType;
        this.senderDate = senderDate;
        this.content = content;
        this.reveicerId = reveicerId;
        this.reveicerType = reveicerType;
        this.markRead = markRead;
        this.readDate = readDate;
    }

    public SysMessage() {
    }
}
