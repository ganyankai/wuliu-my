package com.zrytech.framework.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ApiModel(value = "评价dto")
public class EvaluationDto implements Serializable {

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "主键id", required = false)
    private Integer id;

    @ApiModelProperty(value = "运单编号", required = false)
    private Integer billNo;

    @ApiModelProperty(value = "评价人Id", required = false)
    private Integer appraiserId;

    @ApiModelProperty(value = "被评价人id", required = false)
    private Integer appraiserById;

    @ApiModelProperty(value = "评语(评价内容)", required = false)
    private String content;

    @ApiModelProperty(value = "评价类型", required = false)
    private String evaluateType;

    @ApiModelProperty(value = "评价等级", required = false)
    private Integer evaluateLevel;

    @ApiModelProperty(value = "创建日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
}
