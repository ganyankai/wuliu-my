package com.zrytech.framework.app.dto.customer;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.zrytech.framework.app.constants.RegExConstants;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAccountDto {
	
	/** 用户名 */
	@NotBlank(message = "用户名不能为空")
	@Pattern(regexp = RegExConstants.USER_ACCOUNT, message = RegExConstants.USER_ACCOUNT_ERR_MSG)
	private String userAccount;

}
