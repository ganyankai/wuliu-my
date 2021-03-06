package com.zrytech.framework.app.dto.cargomatter;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 新增报价单入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CargoMatterAddDto {

	/** 货源Id */
	@NotNull(message = "货源Id不能为空")
	private Integer cargoId;

	/** 价格 */
	@NotNull(message = "价格不能为空")
	private BigDecimal matterPrice;

	/** 单价 */
	@NotNull(message = "单价不能为空")
	private BigDecimal unitPrice;

}
