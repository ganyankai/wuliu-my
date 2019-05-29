package com.zrytech.framework.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.utils.DictionaryUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 评价
 */
@Setter
@Getter
@Deprecated
public class Evaluation implements Serializable {

	private static final long serialVersionUID = -1555792098489335740L;

	/** 主键id */
	private Integer id;

	/** 运单id */
	private Integer waybillId;

	/** 运单编号 */
	private Integer billNo;

	/** 评价人Id */
	private Integer appraiserId;

	/** 被评价人id */
	private Integer appraiserById;

	/** 评语 */
	private String content;

	/** 评价类型 */
	private String evaluateType;

	/** 评价等级 */
	private Integer evaluateLevel;

	/** 创建日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;

	public String getEvaluateTypeCN() {
		if (StringUtils.isNotBlank(evaluateType)) {
			return DictionaryUtil.getValue(CargoConstant.EVALUATE_TYPE, evaluateType);
		}
		return "";
	}
}
