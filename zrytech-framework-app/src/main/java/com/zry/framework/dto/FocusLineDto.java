package com.zry.framework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ApiModel(value = "关注线路dto")
public class FocusLineDto implements Serializable {

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "主键id", required = false)
    private Integer id;

    @ApiModelProperty(value = "出发省", required = false)
    private String startProvince;

    @ApiModelProperty(value = "出发市", required = false)
    private String startCity;

    @ApiModelProperty(value = "出发县", required = false)
    private String startCountry;

    @ApiModelProperty(value = "达到省", required = false)
    private String endProvince;

    @ApiModelProperty(value = "达到市", required = false)
    private String endCity;

    @ApiModelProperty(value = "达到县", required = false)
    private String endCountry;

    @ApiModelProperty(value = "创建人", required = false)
    private Integer createBy;

    @ApiModelProperty(value = "创建日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
}
