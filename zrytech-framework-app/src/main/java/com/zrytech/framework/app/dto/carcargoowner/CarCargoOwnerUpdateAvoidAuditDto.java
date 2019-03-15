package com.zrytech.framework.app.dto.carcargoowner;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 修改车主货主免审核入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarCargoOwnerUpdateAvoidAuditDto {
	
	
	@NotNull(message = "是否免审核不能为空")
	private Boolean avoidAudit;
	
	@NotNull(message = "Id不能为空")
	private Integer id;

}
