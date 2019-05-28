package com.zrytech.framework.app.dto.cargosource;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CargoSourceSearchDto {
	
	@Pattern(regexp = "^(qty)|(create_date)|()$", message = "排序方式有误")
	private String sort;
	
	@Pattern(regexp = "^(asc)|(desc)|()$", message = "排序方向有误")
	private String direction;

	/** 货物介质 */
	private String name;

	/** 发标方式 */
	private String tenderWay;

	/** 货源状态 */
	private String status;

	private String startProvince;

	private String startCity;

	private String startCountry;

	private String endProvince;

	private String endCity;

	private String endCountry;

	private Integer createBy;

}
