package com.zry.framework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@ApiModel(value = "常用地址dto")
public class OftenAddressDto implements Serializable {

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "主键id", required = false)
    private Integer id;

    @ApiModelProperty(value = "客户id", required = false)
    private Integer customerId;

    @ApiModelProperty(value = "货物介质", required = false)
    private String cargoName;

    @ApiModelProperty(value = "出发省份", required = false)
    private String beginProvince;

    @ApiModelProperty(value = "出发城市", required = false)
    private String beginCity;

    @ApiModelProperty(value = "出发县", required = false)
    private String beginCounty;

    @ApiModelProperty(value = "到达省份", required = false)
    private String endProvince;

    @ApiModelProperty(value = "到达城市", required = false)
    private String endCity;

    @ApiModelProperty(value = "到达县", required = false)
    private String endCounty;

    @ApiModelProperty(value = "创建日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String createDate;
}
