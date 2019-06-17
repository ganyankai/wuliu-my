package com.zrytech.framework.app.dto.car;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.zrytech.framework.app.constants.RegExConstants;
import org.hibernate.validator.constraints.NotBlank;

import com.zrytech.framework.app.constants.CarConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * 车主 - 修改车辆信息入参（需要审核的内容）
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarCheckUpdateDto {

	/** 车辆主键 */
	@NotNull(message = "车辆Id不能为空")
	private Integer id;

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
	//@Pattern(regexp = CarConstants.REG_EX_CAR_TYPE, message = CarConstants.REG_EX_CAR_TYPE_ERR_MSG)
	private String carType;

	/** 是否分仓 */
	@NotNull(message = "是否分仓不能为空")
	private Boolean mulStore;

	/** 仓位数量 */
	@NotNull(message = "仓位数量不能为空")
	private Integer storeQty;

	private Integer driverId;

	private Integer supercargoId;

}
