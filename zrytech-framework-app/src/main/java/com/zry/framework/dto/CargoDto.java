package com.zry.framework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.price.entity.Loading;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ApiModel(value = "货源dto")
public class CargoDto implements Serializable {

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "货源Id", required = false)
    private Integer id;

    @ApiModelProperty(value = "货物介质", required = false)
    private String name;

    @ApiModelProperty(value = "货物logo", required = false)
    private String logo;

    @ApiModelProperty(value = "货物数量", required = false)
    private Integer qty;

    @ApiModelProperty(value = "重量单位", required = false)
    private String weightUnit;

    @ApiModelProperty(value = "发标方式", required = false)
    private String tenderWay;

    @ApiModelProperty(value = "付款方式", required = false)
    private String payType;

    @ApiModelProperty(value = "是否多点装货", required = false)
    private Boolean mulShipment;

    @ApiModelProperty(value = "多点装货地址集合", required = false)
    private List<Loading> mulShipmentList;

    @ApiModelProperty(value = "是否多点卸货", required = false)
    private Boolean mulUnload;

    @ApiModelProperty(value = "多点卸货地址集合", required = false)
    private List<Loading> mulUnloadList;

    @ApiModelProperty(value = "是否有包装", required = false)
    private Boolean packaged;

    @ApiModelProperty(value = "发标价", required = false)
    private Double matterPrice;

    @ApiModelProperty(value = "中标价", required = false)
    private Double realPrice;

    @ApiModelProperty(value = "市场价", required = false)
    private Double marketPrice;

    @ApiModelProperty(value = "价格单位", required = false)
    private String priceUnit;

    @ApiModelProperty(value = "价格类型", required = false)
    private String priceType;

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

    @ApiModelProperty(value = "路线", required = false)
    private String line;

    @ApiModelProperty(value = "是否拼单", required = false)
    private Boolean canShare;

    @ApiModelProperty(value = "车辆类型", required = false)
    private String carType;

    @ApiModelProperty(value = "状态", required = false)
    private String status;

    @ApiModelProperty(value = "送达日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date arrivalDate;

    @ApiModelProperty(value = "提货日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pickupDate;

    @ApiModelProperty(value = "截止日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;

    @ApiModelProperty(value = "备注", required = false)
    private String remark;

    @ApiModelProperty(value = "创建人", required = false)
    private Integer createBy;

    @ApiModelProperty(value = "创建日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty(value = "描述:拒绝填写理由", required = false)
    private String describe;
}
