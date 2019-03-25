package com.zrytech.framework.app.dto.cargomatter;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 报价单需要审批的内容
 * @author cat
 *
 */
@Setter
@Getter
public class CargoMatterNeedApproveUpdateDto {
	
	/**报价单Id*/
	@NotNull(message = "Id不能为空")
	private Integer id;
	
	/**价格*/
	@NotNull(message = "价格不能为空")
	private BigDecimal matterPrice;
	
}
