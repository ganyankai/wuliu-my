package com.zrytech.framework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ApiModel(value = "运单dto")
public class WaybillDto implements Serializable {

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "主键id", required = false)
    private Integer id;

    @ApiModelProperty(value = "订单编号", required = false)
    private String billNo;

    @ApiModelProperty(value = "货源Id", required = false)
    private Integer cargoId;

    @ApiModelProperty(value = "货主Id", required = false)
    private Integer cargoOwnnerId;

    @ApiModelProperty(value = "货主企业名称", required = false)
    private String cargoOwnerName;

    @ApiModelProperty(value = "车主Id", required = false)
    private Integer carId;

    @ApiModelProperty(value = "车主企业名称", required = false)
    private String carOwnerName;

    @ApiModelProperty(value = "预付款", required = false)
    private Double advanceMoeny;

    @ApiModelProperty(value = "尾款", required = false)
    private Double finalMoney;

    @ApiModelProperty(value = "总金额", required = false)
    private Double totalMoney;

    @ApiModelProperty(value = "支付类型：1，微信 2，支付宝", required = false)
    private String payType;

    @ApiModelProperty(value = "付款方式：货到付款", required = false)
    private String payWay;

    @ApiModelProperty(value = "备注", required = false)
    private String remark;

    @ApiModelProperty(value = "数量", required = false)
    private Integer qty;

    @ApiModelProperty(value = "重量单位", required = false)
    private String weightUnit;

    @ApiModelProperty(value = "运单类型", required = false)
    private String billType;

    @ApiModelProperty(value = "收货凭证", required = false)
    private String proofImgs;

    @ApiModelProperty(value = "状态", required = false)
    private String status;

    @ApiModelProperty(value = "创建日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
}
