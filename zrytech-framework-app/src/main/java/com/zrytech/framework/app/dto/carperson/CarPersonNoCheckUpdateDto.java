package com.zrytech.framework.app.dto.carperson;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * 修改司机压货人不需要审批的字段入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarPersonNoCheckUpdateDto {

	/** 司机或压货人Id */
	@NotNull(message = "Id不能为空")
	private Integer id;

	@NotBlank(message = "姓名不能为空")
	private String name;

}
