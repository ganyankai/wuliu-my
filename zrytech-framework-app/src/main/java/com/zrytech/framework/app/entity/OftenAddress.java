package com.zrytech.framework.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@ApiModel(value = "常用地址entry")
@Entity
@Table(name = "`address`")
public class OftenAddress implements Serializable {

    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "主键id", required = false)
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "客户id", required = false)
    @Column(name = "`customer_id`")
    private Integer customerId;

    @ApiModelProperty(value = "货物介质", required = false)
    @Column(name = "`cargo_name`")
    private String cargoName;

    @ApiModelProperty(value = "出发省份", required = false)
    @Column(name = "`begin_province`")
    private String beginProvince;

    @ApiModelProperty(value = "出发城市", required = false)
    @Column(name = "`begin_city`")
    private String beginCity;

    @ApiModelProperty(value = "出发县", required = false)
    @Column(name = "`begin_county`")
    private String beginCounty;

    @ApiModelProperty(value = "到达省份", required = false)
    @Column(name = "`end_province`")
    private String endProvince;

    @ApiModelProperty(value = "到达城市", required = false)
    @Column(name = "`end_city`")
    private String endCity;

    @ApiModelProperty(value = "到达县", required = false)
    @Column(name = "`end_county`")
    private String endCounty;

    @ApiModelProperty(value = "创建日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "`create_date`")
    private String createDate;
}
