package com.zrytech.framework.app.dto.waybill;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WaybillUpdateDto {

	@NotNull(message = "Id不能为空")
	private Integer id;

	/** 预付款 */
	@NotNull(message = "预付款不能为空")
	private BigDecimal advanceMoeny;

	/** 尾款 */
	@NotNull(message = "尾款不能为空")
	private BigDecimal finalMoney;

	/** 总金额 */
	@NotNull(message = "总金额不能为空")
	private BigDecimal totalMoney;

	/** 支付类型 */
	@NotBlank(message = "支付类型不能为空")
	private String payType;

	/** 付款方式 */
	@NotBlank(message = "付款方式不能为空")
	private String payWay;

	/** 备注 */
	@NotBlank(message = "备注不能为空")
	private String remark;

	/** 数量 */
	@NotNull(message = "数量不能为空")
	private Integer qty;

}
