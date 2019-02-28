package com.zrytech.framework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 车源起始地
 */
@Data
@Entity
@Table(name = "`car_record_place`")
public class CarRecordPlace {
	
	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	/**车源Id*/
	@Column(name = "`car_source_id`")
    private Integer carSourceId;

	/**出发省*/
	@Column(name = "`start_province`")
    private String startProvince;

	/**出发市*/
	@Column(name = "`start_city`")
	private String startCity;

	/**出发县*/
	@Column(name = "`start_country`")
	private String startCountry;

	/**达到省*/
	@Column(name = "`end_province`")
	private String endProvince;

	/**达到市*/
	@Column(name = "`end_city`")
	private String endCity;

	/**达到县*/
	@Column(name = "`end_country`")
	private String endCountry;

}