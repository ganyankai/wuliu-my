package com.zrytech.framework.app.dto.cargosource;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CargoSourceCheckUpdateDto {

	@NotNull(message = "Id不能为空")
	private Integer id;
	
	/** 货物介质 */
	@NotBlank(message = "货物介质不能为空")
	private String name;

	/** 重量单位 */
	@NotBlank(message = "重量单位不能为空")
	private String weightUnit;

	/** 发标方式 */
	@NotBlank(message = "发标方式不能为空")
	private String tenderWay;

	/** 价格单位 */
	@NotBlank(message = "价格单位不能为空")
	private String priceUnit;

	/** 车辆类型 */
	@NotBlank(message = "车辆类型不能为空")
	private String carType;

	/** 付款方式 */
	private String payType;

	/** 货物logo */
	private String logo;

	/** 货物数量 */
	@NotNull(message = "货物数量不能为空")
	@Range(min = 1, message = "货物数量不能小于1")
	private Integer qty;

	/** 是否有包装 */
	@NotNull(message = "是否有包装不能为空")
	private Boolean packaged;

	/** 是否拼单 */
	@NotNull(message = "是否拼单不能为空")
	private Boolean canShare;

	/** 截止日期 */
	@NotNull(message = "截止日期不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endDate;
	
	/** 备注 */
	private String remark;
	
	/** 发标价 */
	private BigDecimal matterPrice;
	
	/** 市场价 */
	private BigDecimal marketPrice;

}
