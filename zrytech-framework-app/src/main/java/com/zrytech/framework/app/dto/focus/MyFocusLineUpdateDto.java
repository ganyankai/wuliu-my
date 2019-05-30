package com.zrytech.framework.app.dto.focus;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyFocusLineUpdateDto {

	@NotNull(message = "Id不能为空")
	private Integer id;

	/** 出发省 */
	@NotBlank(message = "出发省不能为空")
	private String startProvince;

	/** 出发市 */
	@NotBlank(message = "出发市不能为空")
	private String startCity;

	/** 出发县 */
	@NotBlank(message = "出发县不能为空")
	private String startCountry;

	/** 到达省 */
	@NotBlank(message = "到达省不能为空")
	private String endProvince;

	/** 到达市 */
	@NotBlank(message = "到达市不能为空")
	private String endCity;

	/** 到达县 */
	@NotBlank(message = "到达县不能为空")
	private String endCountry;

}
