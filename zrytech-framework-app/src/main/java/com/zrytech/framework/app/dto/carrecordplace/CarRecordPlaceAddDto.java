package com.zrytech.framework.app.dto.carrecordplace;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * 新增车源路线入参
 * @author cat
 */
@Setter
@Getter
public class CarRecordPlaceAddDto {

	/**出发省*/
	@NotBlank(message = "出发省不能为空")
    private String startProvince;

	/**出发市*/
	@NotBlank(message = "出发市不能为空")
	private String startCity;

	/**出发县*/
	@NotBlank(message = "出发县不能为空")
	private String startCountry;

	/**到达省*/
	@NotBlank(message = "到达省不能为空")
	private String endProvince;

	/**到达市*/
	@NotBlank(message = "到达市不能为空")
	private String endCity;

	/**到达县*/
	@NotBlank(message = "到达县不能为空")
	private String endCountry;
	
}
