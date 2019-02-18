package com.zry.framework.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


/**
 * 车源
 *
 */
@Data
@Entity
@Table(name = "`car_source`")
public class CarSource {

	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	/**车主Id*/
	@Column(name = "`create_by`")
    private Integer createBy;

	/**车辆类型*/
	@Column(name = "`car_type`")
    private String carType;
	
	/**核载量*/
	@Column(name = "`qty`")
    private Integer qty;

	/**状态*/
	@Column(name = "`status`")
    private String status;

    /**创建日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`create_date`")
    private Date createDate;
	
	/**车源路线*/
	@Transient
	private List<CarRecordPlace> carRecordPlaces;

}