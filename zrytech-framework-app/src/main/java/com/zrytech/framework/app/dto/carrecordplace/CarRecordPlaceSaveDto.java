package com.zrytech.framework.app.dto.carrecordplace;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * 更新路线或新增路线入参
 * 
 * @author cat
 */
@Setter
@Getter
public class CarRecordPlaceSaveDto {

	/** 车源Id */
	@NotNull(message = "车源Id不能为空")
	private Integer carSourceId;

	/** 路线 */
	@NotNull(message = "车源起止地不能为空")
	@Size(min = 1, max = 1, message = "当前只能有一个车源起止地")
	private List<CarRecordPlaceUpdateDto> carRecordPlaces;

}
