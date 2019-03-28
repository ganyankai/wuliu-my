package com.zrytech.framework.app.dto.carcargoowner;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.zrytech.framework.app.constants.CarCargoOwnerConstants;
import com.zrytech.framework.app.constants.RegExConstants;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarCargoOwnerAddDto {

	/** 企业名称 */
	private String name;

	/** 信用代码 */
	private String creditCode;

	/** 营业执照 */
	private String businessLicense;

	/** 法人姓名 */
	private String legalerName;

	/** 法人身份证号码 */
	private String legalerIdCardNo;

	/** 法人身份证照片 */
	private String legalerIdCardFront;

	/** 联系电话 */
	@Pattern(regexp = RegExConstants.CHINA_PHONE_NUMBER, message = "联系电话格式有误")
	private String tel;
	
	/** 经度 */
	private BigDecimal longitude;

	/** 纬度 */
	private BigDecimal latitude;

	/** 省份 */
	private String province;

	/** 城市 */
	private String city;

	/** 县 */
	private String county;

	/** 地址详情 */
	private String addressDetail;

	/** 企业简介 */
	private String intro;
	
	/** 头像 */
	private String headImg;
	
	/** 性别 */
	private Integer gender;

	/** 类型 */
	@NotBlank(message = "账号类型不能为空")
	@Pattern(regexp = CarCargoOwnerConstants.REG_EX_CUSTOMER_TYPE, message = CarCargoOwnerConstants.REG_EX_CUSTOMER_TYPE_ERR_MSG)
	private String customerType;
	
	/** 是否跳过资料认证 */
	@NotNull(message = "是否跳过资料认证不能为空")
	private Boolean jump;

}
