package com.zrytech.framework.app.dto.cargolocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.app.constants.CargoConstant;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class CargoLocationAddDto {

	/** 类型 */
	@NotBlank(message = "装卸类型不能为空")
	@Pattern(regexp = CargoConstant.REG_EX_LOAD_TYPE, message = CargoConstant.REG_EX_LOAD_TYPE_ERR_MSG)
	private String type;

	/** 经度 */
	@NotNull(message = "经度不能为空")
	private BigDecimal longitude;

	/** 纬度 */
	@NotNull(message = "纬度不能为空")
	private BigDecimal latitude;

	/** 省 */
	@NotBlank(message = "省不能为空")
	private String province;

	/** 市 */
	@NotBlank(message = "市不能为空")
	private String city;

	/** 县 */
	@NotBlank(message = "县不能为空")
	private String county;

	/** 详细地址 */
	@NotBlank(message = "详细地址不能为空")
	private String addressDetail;

	/** 装卸数量 */
	@NotNull(message = "装卸数量不能为空")
	private Integer qty;

	/** 备注 */
	private String remark;

	/** 序列 */
	private Integer seqNo;

	/** 装卸日期 */
	@NotNull(message = "装卸日期不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date loadDate;

	/** 截止日期 */
	@NotNull(message = "截止日期不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endDate;

}
