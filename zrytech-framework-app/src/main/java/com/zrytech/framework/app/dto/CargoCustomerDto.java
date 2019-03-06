package com.zrytech.framework.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.app.entity.Certification;
import com.zrytech.framework.app.entity.OftenAddress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ApiModel(value = "customer客户dto")
public class CargoCustomerDto implements Serializable {

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "客户id", required = false)
    private Integer id;

    @ApiModelProperty(value = "客户登录账号", required = false)
    private String loginCounter;

    @ApiModelProperty(value = "客户登录密码", required = false)
    private String pwd;

    @ApiModelProperty(value = "客户登录确认密码", required = false)
    private String confirmPwd;

    @ApiModelProperty(value = "客户电话", required = false)
    private String tel;

    @ApiModelProperty(value = "手机验证码", required = false)
    private String code;

    @ApiModelProperty(value = "客户名称(冗余字段)", required = false)
    private String name;

    @ApiModelProperty(value = "客户logo", required = false)
    private String logo;

    @ApiModelProperty(value = "客户推荐人手机号", required = false)
    private String refereesTel;

    @ApiModelProperty(value = "客户类型", required = false)
    private String customerType;

    @ApiModelProperty(value = "客户状态", required = false)
    private Boolean isActive;

    @ApiModelProperty(value = "客户创建人", required = false)
    private Integer createBy;

    @ApiModelProperty(value = "创建日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty(value = "常用的提货地址和接货地址", required = false)
    private List<OftenAddress> extractList;

    @ApiModelProperty(value = "认证资料", required = false)
    private Certification certificationData;
}
