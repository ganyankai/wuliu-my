package com.zry.framework.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 车辆
 *
 */
@Data
@Entity
@Table(name = "`car`")
public class Car {
	
	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	/**车牌号*/
	@Column(name = "`car_no`")
    private String carNo;

	/**车载量*/
	@Column(name = "`car_load`")
    private Integer carLoad;

	/**车载量单位*/
	@Column(name = "`car_unit`")
    private String carUnit;

	/**车辆类型*/
	@Column(name = "`car_type`")
    private String carType;

	/**司机Id*/
	@Column(name = "`driver_id`")
    private Integer driverId;

	/**押货人Id*/
	@Column(name = "`supercargo_id`")
    private Integer supercargoId;

	/**经度*/
	@Column(name = "`current_longitude`")
    private BigDecimal currentLongitude;

	/**纬度*/
	@Column(name = "`current_latitude`")
    private BigDecimal currentLatitude;

	/**当前位置*/
	@Column(name = "`current_address`")
    private String currentAddress;

	/**是否分仓*/
	@Column(name = "`mul_store`")
    private Boolean mulStore;

	/**仓位数量*/
	@Column(name = "`store_qty`")
    private Integer storeQty;

	/**车辆状态*/
	@Column(name = "`status`")
    private String status;

	/**是否删除*/
	@Column(name = "`is_delete`")
    private Boolean isDelete;

	/**车主Id*/
	@Column(name = "`create_by`")
    private Integer createBy;

	/**创建日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`create_date`")
    private Date createDate;
	
	
	
    
}