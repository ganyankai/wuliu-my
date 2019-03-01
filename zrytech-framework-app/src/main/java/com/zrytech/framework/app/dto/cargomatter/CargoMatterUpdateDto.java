package com.zrytech.framework.app.dto.cargomatter;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 修改报价单入参
 * @author cat
 *
 */
@Setter
@Getter
public class CargoMatterUpdateDto {
	
	/**报价单Id*/
	@NotNull(message = "报价单Id不能为空")
	private Integer id;
	
	/**价格*/
	@NotNull(message = "价格不能为空")
	private BigDecimal matterPrice;
}
