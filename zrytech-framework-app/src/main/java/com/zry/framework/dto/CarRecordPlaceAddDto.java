package com.zry.framework.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


/**
 * 添加车源起始地
 */
@Setter
@Getter
public class CarRecordPlaceAddDto {

	/**车源Id*/
	@NotNull(message = "车源不能为空")
    private Integer carSourceId;

	/**出发省*/
	@NotEmpty(message = "出发省不能为空")
    private String startProvince;

	/**出发市*/
	@NotEmpty(message = "出发市不能为空")
	private String startCity;

	/**出发县*/
	@NotEmpty(message = "出发县不能为空")
	private String startCountry;

	/**达到省*/
	@NotEmpty(message = "达到省不能为空")
	private String endProvince;

	/**达到市*/
	@NotEmpty(message = "达到市不能为空")
	private String endCity;

	/**达到县*/
	@NotEmpty(message = "达到县不能为空")
	private String endCountry;
	
}
