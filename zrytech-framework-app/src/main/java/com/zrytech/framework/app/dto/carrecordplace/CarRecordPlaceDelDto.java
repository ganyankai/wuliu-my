package com.zrytech.framework.app.dto.carrecordplace;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 删除车源起止地入参
 * 
 * @author cat
 */
@Setter
@Getter
public class CarRecordPlaceDelDto {

	/** 车源Id */
	@NotNull(message = "车源Id不能为空")
	private Integer carSourceId;

	/** 起止地Id */
	@NotNull(message = "起止地Id不能为空")
	private Integer carRecordPlaceId;

}
