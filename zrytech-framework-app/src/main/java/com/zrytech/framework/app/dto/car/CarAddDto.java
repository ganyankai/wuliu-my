package com.zrytech.framework.app.dto.car;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.zrytech.framework.app.constants.CarConstants;
import com.zrytech.framework.app.constants.RegExConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * 车主 - 添加车辆入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarAddDto {

	/** 车牌号 */
	@NotBlank(message = "车牌号不能为空")
	@Pattern(regexp = RegExConstants.CAR_NO, message = RegExConstants.CAR_NO_ERR_MSG)
	private String carNo;

	/** 核载量 */
	@NotNull(message = "核载量不能为空")
	private Integer carLoad;

	/** 核载量单位 */
	@NotBlank(message = "核载量单位不能为空")
	@Pattern(regexp = CarConstants.REG_EX_CAR_UNIT, message = CarConstants.REG_EX_CAR_UNIT_ERR_MSG)
	private String carUnit;

	/** 车辆类型 */
	@NotBlank(message = "车辆类型不能为空")
	@Pattern(regexp = CarConstants.REG_EX_CAR_TYPE, message = CarConstants.REG_EX_CAR_TYPE_ERR_MSG)
	private String carType;

	/** 是否分仓 */
	@NotNull(message = "是否分仓不能为空")
	private Boolean mulStore;

	/** 仓位数量 */
	@NotNull(message = "仓位数量不能为空")
	private Integer storeQty;

}
