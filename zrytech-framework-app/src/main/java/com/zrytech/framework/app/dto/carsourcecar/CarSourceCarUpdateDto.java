package com.zrytech.framework.app.dto.carsourcecar;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

/**
 * 车源之新增车辆或更新车辆入参
 * <p>
 * id为空表示新增，id不为空表示更新
 * </p>
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourceCarUpdateDto {
	
	/** 车源车辆关系Id */
	private Integer id;

	/** 车辆Id */
	@NotNull(message = "车辆Id不能为空")
	private Integer carId;

	/** 司机Id */
	private Integer driverId;

	/** 压货人Id */
	private Integer supercargoId;
	
	// 2019-5-20 13:53:59 新增字段
	
	/** 空闲车载量 */
	@NotNull(message = "空闲运输量不能为空")
	@Range(min = 1, message = "空闲运输量不能小于1")
	private Integer freeQty;
}
