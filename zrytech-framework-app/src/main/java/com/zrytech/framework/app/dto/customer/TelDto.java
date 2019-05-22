package com.zrytech.framework.app.dto.customer;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.zrytech.framework.app.constants.RegExConstants;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TelDto {
	
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = RegExConstants.CHINA_PHONE_NUMBER, message = RegExConstants.CHINA_PHONE_NUMBER_ERR_MSG)
	private String tel;

}
