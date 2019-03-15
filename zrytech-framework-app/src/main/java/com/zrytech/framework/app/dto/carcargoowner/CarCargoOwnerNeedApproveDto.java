package com.zrytech.framework.app.dto.carcargoowner;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.zrytech.framework.app.constants.RegExConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * 车主货主需要审批的字段
 * @author cat
 *
 */
@Setter
@Getter
public class CarCargoOwnerNeedApproveDto {
	
    /**企业名称*/
	@NotBlank(message = "企业名称不能为空")
    private String name;

    /**信用代码*/
	@NotBlank(message = "信用代码不能为空")
    private String creditCode;

    /**营业执照*/
	@NotBlank(message = "营业执照不能为空")
    private String businessLicense;

    /**法人姓名*/
	@NotBlank(message = "法人姓名不能为空")
	private String legalerName;

    /**法人身份证号码*/
	@NotBlank(message = "法人身份证号码不能为空")
	private String legalerIdCardNo;

    /**法人身份证照片*/
	@NotBlank(message = "法人身份证照片不能为空")
    private String legalerIdCardFront;

    /**联系电话*/
	@NotBlank(message = "联系电话不能为空")
	@Pattern(regexp = RegExConstants.CHINA_PHONE_NUMBER, message = RegExConstants.CHINA_PHONE_NUMBER_ERR_MSG)
	private String tel;

   

}
