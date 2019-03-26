package com.zrytech.framework.app.dto.carsource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.zrytech.framework.app.constants.CarConstants;

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
	@Pattern(regexp = CarConstants.REG_EX_CAR_TYPE, message = CarConstants.REG_EX_CAR_TYPE_ERR_MSG)
	private String carType;

	/** 运输量 */
	@NotNull(message = "运输量不能为空")
	@Range(min = 1, message = "运输量不能小于1")
	private Integer qty;

	/** 车辆核载量单位 */
	@NotBlank(message = "车辆核载量单位不能为空")
	@Pattern(regexp = CarConstants.REG_EX_CAR_UNIT, message = CarConstants.REG_EX_CAR_UNIT_ERR_MSG)
	private String unit;

}
