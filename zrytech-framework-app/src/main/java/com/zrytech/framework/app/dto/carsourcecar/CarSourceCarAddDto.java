package com.zrytech.framework.app.dto.carsourcecar;

import lombok.Getter;
import lombok.Setter;

/**
 * 新增车源的车辆，司机，压货人入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourceCarAddDto {

	/** 车辆Id */
	private Integer carId;

	/** 司机Id */
	private Integer driverId;

	/** 压货人Id */
	private Integer supercargoId;
}
