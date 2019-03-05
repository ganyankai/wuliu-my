package com.zrytech.framework.app.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ApiModel(value = "报价单entry")
public class Offer implements Serializable {

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "主键id", required = false)
    private Integer id;

    @ApiModelProperty(value = "货源ID", required = false)
    private Integer cargoId;

    @ApiModelProperty(value = "车主Id", required = false)
    private Integer carOwnnerId;

    @ApiModelProperty(value = "应标价格", required = false)
    private Double matterPrice;

    @ApiModelProperty(value = "状态", required = false)
    private String status;

    @ApiModelProperty(value = "中标日期", required = false)
    private Date loadDate;

    @ApiModelProperty(value = "应标日期", required = false)
    private Date createDate;

    @ApiModelProperty(value = "创建人", required = false)
    private Integer createBy;
}
