package com.zrytech.framework.app.dto.customer;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.zrytech.framework.app.constants.RegExConstants;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerAddDto;
import com.zrytech.framework.app.dto.oftenaddress.OftenAddressAddDto;
import lombok.Getter;
import lombok.Setter;

/**
 * 车主货主注册入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CustomerRegisterDto {

	/** 用户名 */
	@NotBlank(message = "用户名不能为空")
	@Pattern(regexp = RegExConstants.USER_ACCOUNT, message = RegExConstants.USER_ACCOUNT_ERR_MSG)
	private String userAccount;

	/** 密码 */
	@NotBlank(message = "密码不能为空")
	@Pattern(regexp = RegExConstants.PASSWORD, message = RegExConstants.PASSWORDERR_MSG)
	private String password;

	/** 手机号 */
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = RegExConstants.CHINA_PHONE_NUMBER, message = RegExConstants.CHINA_PHONE_NUMBER_ERR_MSG)
	private String tel;

	/** 验证码 */
	@NotBlank(message = "验证码不能为空")
	private String code;

	/** 客户名称 */
	private String userName;

	/** logo */
	private String logo;

	/** 账号类型 */
	private String customerType;

	/** 推荐人手机号 */
	@Pattern(regexp = RegExConstants.CHINA_PHONE_NUMBER, message = "推荐人" + RegExConstants.CHINA_PHONE_NUMBER_ERR_MSG)
	private String referrerTel;

	/** 车主或货主认证资料 */
	@Valid
	private CarCargoOwnerAddDto carCargoOwner;

	/** 常用地址 */
	@Valid
	private List<OftenAddressAddDto> oftenAddress;

}
