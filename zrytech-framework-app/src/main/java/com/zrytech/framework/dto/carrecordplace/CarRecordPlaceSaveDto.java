package com.zrytech.framework.dto.carrecordplace;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 更新路线或新增路线入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarRecordPlaceSaveDto {
	
	/**车源Id*/
	@NotNull(message= "车源Id不能为空")
	private Integer carSourceId;
	
	/**路线*/
	private List<CarRecordPlaceUpdateDto> carRecordPlaces;
}