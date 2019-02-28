package com.zrytech.framework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * 车源车辆关系
 *
 */
@Data
@Entity
@Table(name = "`car_source_car`")
public class CarSourceCar {
	
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

}