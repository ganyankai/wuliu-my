package com.zrytech.framework.app.dto.carsourcecar;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 删除车源车辆入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourceCarDelDto {

	/** 车源Id */
	@NotNull(message = "车源Id不能为空")
	private Integer carSourceId;

	/** 车源车辆关系Id */
	@NotNull(message = "Id不能为空")
	private Integer carSourceCarId;
	
}
