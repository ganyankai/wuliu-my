package com.zrytech.framework.app.dto.hotplace;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class HotPlaceUpdateDto {
    @NotNull(message = "Id不能为空")
	/** id */
	private Integer id;

	/** 省份 */
	private String province;

	/** 城市 */
	@NotBlank(message = "城市不能为空")
	private String city;

	/** 县 */
	private String country;

}
