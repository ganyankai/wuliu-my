package com.zrytech.framework.app.dto.hotplace;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class HotPlaceCommonDto {

	@NotNull(message = "Id不能为空")
	private Integer id;


}
