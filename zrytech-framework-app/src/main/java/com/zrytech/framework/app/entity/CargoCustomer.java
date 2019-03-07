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
@ApiModel(value = "customer客户对象")
public class CargoCustomer implements Serializable{

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "客户id", required = false)
    private Integer id;

    @ApiModelProperty(value = "客户登录账号", required = false)
    private String loginCounter;

    @ApiModelProperty(value = "客户登录密码", required = false)
    private String pwd;

    @ApiModelProperty(value = "客户电话", required = false)
    private String tel;

    @ApiModelProperty(value = "客户名称(冗余字段)", required = false)
    private String name;

    @ApiModelProperty(value = "客户logo", required = false)
    private String logo;

    @ApiModelProperty(value = "客户类型", required = false)
    private String customerType;

    @ApiModelProperty(value = "客户状态", required = false)
    private Boolean isActive;

    @ApiModelProperty(value = "客户创建人", required = false)
    private Integer createBy;

    @ApiModelProperty(value = "创建日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty(value = "客户认证资料", required = false)
    private Certification certification;
}
