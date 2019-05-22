package com.zrytech.framework.app.dto.cargosource;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CargoSourceNoCheckUpdateDto {
	
	@NotNull(message = "Id不能为空")
	private Integer id;

	/** 备注 */
	private String remark;
	
	/** 发标价 */
	private BigDecimal matterPrice;
	
	/** 市场价 */
	private BigDecimal marketPrice;
}
