package com.zry.framework.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Setter
@Getter
@ApiModel(value = "货源entry")
@Entity
@Table(name = "cargo")
public class Cargo implements Serializable{

    private static final long serialVersionUID = -1555792098489335740L;
    
    /**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "货源Id", required = false)
    private Integer id;

    @ApiModelProperty(value = "货物介质", required = false)
    @Column(name = "`name`")
    private String name;

    @ApiModelProperty(value = "货物logo", required = false)
    @Column(name = "`logo`")
    private String logo;

    @ApiModelProperty(value = "货物数量", required = false)
    @Column(name = "`qty`")
    private Integer qty;

    @ApiModelProperty(value = "重量单位", required = false)
    @Column(name = "`weight_unit`")
    private String weightUnit;

    @ApiModelProperty(value = "重量单位", required = false)
    @Transient
    private String weightUnitCN;

    @ApiModelProperty(value = "发标方式", required = false)
    @Column(name = "`tender_way`")
    private String tenderWay;

    @ApiModelProperty(value = "发标方式", required = false)
    @Transient
    private String tenderWayCN;

    @ApiModelProperty(value = "付款方式", required = false)
    @Column
    private String payType;

    @ApiModelProperty(value = "付款方式", required = false)
    @Transient
    private String payTypeCN;

    @ApiModelProperty(value = "是否多点装货", required = false)
    @Column
    private Boolean mulShipment;

    @ApiModelProperty(value = "多点装货地址集合", required = false)
    @Transient
    private List<Loading> mulShipmentList;

    @ApiModelProperty(value = "是否多点卸货", required = false)
    @Column
    private Boolean mulUnload;

    @ApiModelProperty(value = "多点卸货地址集合", required = false)
    @Transient
    private List<Loading> mulUnloadList;

    @ApiModelProperty(value = "是否有包装", required = false)
    @Column
    private Boolean packaged;

    @ApiModelProperty(value = "发标价", required = false)
    @Column
    private Double matterPrice;

    @ApiModelProperty(value = "中标价", required = false)
    @Column
    private Double realPrice;

    @ApiModelProperty(value = "市场价", required = false)
    @Column
    private Double marketPrice;

    @ApiModelProperty(value = "价格单位", required = false)
    @Column
    private String priceUnit;

    @ApiModelProperty(value = "价格单位", required = false)
    @Transient
    private String priceUnitCN;

    @ApiModelProperty(value = "价格类型", required = false)
    @Column
    private String priceType;

    @ApiModelProperty(value = "价格类型", required = false)
    @Transient
    private String priceTypeCN;

    @ApiModelProperty(value = "出发省", required = false)
    @Column
    private String startProvince;

    @ApiModelProperty(value = "出发市", required = false)
    @Column
    private String startCity;

    @ApiModelProperty(value = "出发县", required = false)
    @Column
    private String startCountry;

    @ApiModelProperty(value = "达到省", required = false)
    @Column
    private String endProvince;

    @ApiModelProperty(value = "达到市", required = false)
    @Column
    private String endCity;

    @ApiModelProperty(value = "达到县", required = false)
    @Column
    private String endCountry;

    @ApiModelProperty(value = "路线", required = false)
    @Column
    private String line;

    @ApiModelProperty(value = "是否拼单", required = false)
    @Column
    private Boolean canShare;

    @ApiModelProperty(value = "车辆类型", required = false)
    @Column
    private String carType;

    @ApiModelProperty(value = "车辆类型", required = false)
    @Transient
    private String carTypeCN;

    @ApiModelProperty(value = "状态", required = false)
    @Column(name= "`status`")
    private String status;

    @ApiModelProperty(value = "状态", required = false)
    @Transient
    private String statusCN;

    @ApiModelProperty(value = "送达日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column
    private Date arrivalDate;

    @ApiModelProperty(value = "提货日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column
    private Date pickupDate;

    @ApiModelProperty(value = "截止日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column
    private Date endDate;

    @ApiModelProperty(value = "备注", required = false)
    @Column
    private String remark;

    @ApiModelProperty(value = "创建人", required = false)
    @Column
    private Integer createBy;

    @ApiModelProperty(value = "创建日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column
    private Date createDate;
}
