package com.zrytech.framework.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ApiModel(value = "消息entry")
public class Message implements Serializable {

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "主键id", required = false)
    private Integer id;

    @ApiModelProperty(value = "消息类型", required = false)
    private String msgType;

    @ApiModelProperty(value = "消息类型", required = false)
    private String msgTypeCN;

    @ApiModelProperty(value = "发送人ID", required = false)
    private Integer senderId;

    @ApiModelProperty(value = "发送人类型", required = false)
    private String senderType;

    @ApiModelProperty(value = "发送人类型", required = false)
    private String senderTypeCN;

    @ApiModelProperty(value = "发送日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date senderDate;

    @ApiModelProperty(value = "内容", required = false)
    private String content;

    @ApiModelProperty(value = "接收人ID", required = false)
    private Integer reveicerId;

    @ApiModelProperty(value = "接收人类型", required = false)
    private String reveicerType;

    @ApiModelProperty(value = "接收人类型", required = false)
    private String reveicerTypeCN;

    @ApiModelProperty(value = "是否已读", required = false)
    private Boolean markRead;

    @ApiModelProperty(value = "查看日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date readDate;
}
