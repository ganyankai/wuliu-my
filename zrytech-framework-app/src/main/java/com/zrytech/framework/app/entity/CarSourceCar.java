package com.zrytech.framework.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zrytech.framework.base.entity.BaseEntity;

import lombok.Data;


/**
 * 车源车辆关系
 *
 */
@Data
@Entity
@Table(name = "`car_source_car`")
public class CarSourceCar extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7211712890104222626L;

	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	/**车源Id*/
	@Column(name = "`car_source_id`")
    private Integer carSourceId;

	/**车辆Id*/
	@Column(name = "`car_id`")
    private Integer carId;

	/**司机Id*/
	@Column(name = "`driver_id`")
    private Integer driverId;

	/**压货人Id*/
	@Column(name = "`supercargo_id`")
    private Integer supercargoId;
	
	// 2019-5-20 13:53:59 新增字段
	
	/** 空闲运输量 */
	@Column(name = "`free_qty`")
	private Integer freeQty;

}