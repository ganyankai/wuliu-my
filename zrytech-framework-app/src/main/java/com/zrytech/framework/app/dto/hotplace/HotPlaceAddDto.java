package com.zrytech.framework.app.dto.hotplace;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;

@Setter
@Getter
public class HotPlaceAddDto {


	/** 省份 */
	private String province;

	/** 城市 */
	@NotBlank(message = "城市不能为空")
	private String city;

	/** 县 */
	private String country;

}
