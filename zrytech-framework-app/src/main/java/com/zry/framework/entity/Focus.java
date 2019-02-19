package com.zry.framework.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ApiModel(value = "我的关注entry")
public class Focus implements Serializable {

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "主键id", required = false)
    private Integer id;

    @ApiModelProperty(value = "关注人id", required = false)
    private Integer focuserId;

    @ApiModelProperty(value = "被关注人id", required = false)
    private Integer beFocuserId;

    @ApiModelProperty(value = "关注类型", required = false)
    private String focusType;

    @ApiModelProperty(value = "关注类型", required = false)
    private String focusTypeCN;

    @ApiModelProperty(value = "创建日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty(value = "企业全称", required = false)
    private String name;
}
