package com.zrytech.framework.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.utils.DictionaryUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
/**
 *  消息统计实体
 */
public class MessageCount implements Serializable {
    private static final long serialVersionUID = -1555792098489335740L;

//  统计所有未读消息
    private Integer allNotRead;

//  统计每个分类下的未读消息

    //审核分类下未读
    private Integer approvingNotRead;
    //竞价分类下未读
    private Integer biddingNotRead;
    //运单分类下未读
    private Integer waybillNotRead;


}
