package com.zrytech.framework.app.dto.cargosource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class CargoRecDto {

	@NotNull(message = "车主id不能为空")
	private Integer carOwnnerId;

}
