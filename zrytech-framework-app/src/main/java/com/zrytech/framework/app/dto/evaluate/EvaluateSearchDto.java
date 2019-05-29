package com.zrytech.framework.app.dto.evaluate;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EvaluateSearchDto {

	/** 运单Id */
	private Integer waybillId;

	/** 评价等级 */
	@Range(min = 1, max = 5, message = "评价等级最高为5最低为1")
	private Integer evaluateLevel;

	/** 评价人Id */
	private Integer appraiserId;

	/** 被评价人id */
	private Integer appraiserById;

	/** 评价类型 */
	private String evaluateType;

}
