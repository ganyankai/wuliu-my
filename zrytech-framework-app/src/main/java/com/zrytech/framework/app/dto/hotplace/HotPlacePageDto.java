package com.zrytech.framework.app.dto.hotplace;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class HotPlacePageDto {

	/** 省份 */
	private String province;
	
	/** 城市 */
	private String city;

	/** 县 */
	private String country;

}
