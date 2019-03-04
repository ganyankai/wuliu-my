package com.zrytech.framework.app.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ApiModel(value = "认证资料对象")
public class Certification implements Serializable{

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "认证资料Id", required = false)
    private Integer  id;

    @ApiModelProperty(value = "企业名称", required = false)
    private String name;

    @ApiModelProperty(value = "信用代码", required = false)
    private String creditCode;

    @ApiModelProperty(value = "营业执照", required = false)
    private String businessLicense;

    @ApiModelProperty(value = "营业执照", required = false)
    private String businessLicenseUrl;

    @ApiModelProperty(value = "法人姓名", required = false)
    private String legalerName;

    @ApiModelProperty(value = "法人身份证号码", required = false)
    private String legalerIdCardNo;

    @ApiModelProperty(value = "法人身份证正面照", required = false)
    private String legalerIdCardFront;

    @ApiModelProperty(value = "法人身份证正面照", required = false)
    private String legalerIdCardFrontUrl;

    @ApiModelProperty(value = "联系电话", required = false)
    private String tel;

    @ApiModelProperty(value = "经度", required = false)
    private Double longitude;

    @ApiModelProperty(value = "纬度", required = false)
    private Double latitude;

    @ApiModelProperty(value = "省份", required = false)
    private String province;

    @ApiModelProperty(value = "城市", required = false)
    private String city;

    @ApiModelProperty(value = "县", required = false)
    private String county;

    @ApiModelProperty(value = "地址详情", required = false)
    private String addressDetail;

    @ApiModelProperty(value = "企业简介", required = false)
    private String intro;

    @ApiModelProperty(value = "类型", required = false)
    private String customerType;

    @ApiModelProperty(value = "类型", required = false)
    private String customerTypeCN;

    @ApiModelProperty(value = "免审核", required = false)
    private Boolean avoidAudit;

    @ApiModelProperty(value = "成交率", required = false)
    private Double closeRate;

    @ApiModelProperty(value = "好评等级", required = false)
    private Integer favorableLevel;

    @ApiModelProperty(value = "状态", required = false)
    private String status;

    @ApiModelProperty(value = "状态", required = false)
    private String statusCN;

    @ApiModelProperty(value = "客户Id", required = false)
    private Integer cusomerId;

    @ApiModelProperty(value = "创建日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
}
