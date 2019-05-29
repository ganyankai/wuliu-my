package com.zrytech.framework.app.dto.evaluate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EvaluateAddDto {

	/** 运单Id */
	@NotNull(message = "运单Id不能为空")
	private Integer waybillId;

	/** 评语 */
	@NotBlank(message = "评语不能为空")
	private String content;

	/** 评价等级 */
	@NotNull(message = "评价等级不能为空")
	@Range(min = 1, max = 5, message = "评价等级最高为5最低为1")
	private Integer evaluateLevel;

}
