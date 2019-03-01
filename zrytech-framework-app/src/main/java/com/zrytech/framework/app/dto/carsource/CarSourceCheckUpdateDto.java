package com.zrytech.framework.app.dto.carsource;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * 修改车源基本信息入参（需要审核的内容）
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourceCheckUpdateDto {

	/** 车源Id */
	@NotNull(message = "车源Id不能为空")
	private Integer id;

	/** 车辆类型 */
	@NotBlank(message = "车辆类型不能为空")
	private String carType;

	/** 运输量 */
	@NotNull(message = "运输量不能为空")
	private Integer qty;

	/** 运输量单位 */
	@NotBlank(message = "运输量单位不能为空")
	private String unit;

}
