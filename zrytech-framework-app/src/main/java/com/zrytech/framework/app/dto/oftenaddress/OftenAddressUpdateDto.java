package com.zrytech.framework.app.dto.oftenaddress;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * 更新常用地址入参
 */
@Setter
@Getter
public class OftenAddressUpdateDto {

	@NotNull(message = "Id不能为空")
	private Integer id;

	/** 货物介质 */
	@NotBlank(message = "货物介质不能为空")
	private String cargoName;

	/** 出发省 */
	@NotBlank(message = "出发省不能为空")
	private String beginProvince;

	/** 出发市 */
	@NotBlank(message = "出发市不能为空")
	private String beginCity;

	/** 出发县 */
	@NotBlank(message = "出发县不能为空")
	private String beginCounty;

	/** 到达省 */
	@NotBlank(message = "到达省不能为空")
	private String endProvince;

	/** 到达市 */
	@NotBlank(message = "到达市不能为空")
	private String endCity;

	/** 到达县 */
	@NotBlank(message = "到达县不能为空")
	private String endCounty;
}
