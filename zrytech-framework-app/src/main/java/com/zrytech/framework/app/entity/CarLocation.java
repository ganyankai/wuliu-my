package com.zrytech.framework.app.entity;

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
 * 车辆位置
 */
@Data
@Entity
@Table(name = "`car_location`")
public class CarLocation {

	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	/**车牌号*/
	@Column(name = "`car_no`")
    private String carNo;
	
	/**经度*/
	@Column(name = "`current_longitude`")
    private BigDecimal currentLongitude;

	/**纬度*/
	@Column(name = "`current_latitude`")
    private BigDecimal currentLatitude;

	/**当前位置*/
	@Column(name = "`current_address`")
    private String currentAddress;
	
	/**创建日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`create_date`")
    private Date createDate;
	
}
