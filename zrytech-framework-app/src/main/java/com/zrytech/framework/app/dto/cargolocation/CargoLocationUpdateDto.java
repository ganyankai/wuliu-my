package com.zrytech.framework.app.dto.cargolocation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class CargoLocationUpdateDto {

	@NotNull(message = "货源Id不能为空")
	private Integer cargoSourceId;

	/** 装卸地列表 */
	@NotNull(message = "装卸地不能为空")
	@Size(min = 2, message = "至少有一个装货地一个卸货地")
	private List<CargoLocationAddDto> cargoLocationList;

}
