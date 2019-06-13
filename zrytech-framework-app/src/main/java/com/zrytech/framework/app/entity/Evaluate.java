package com.zrytech.framework.app.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.app.constants.CarConstants;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.utils.DictionaryUtil;
import com.zrytech.framework.base.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 评论
 * 
 * @author cat
 *
 */
@Setter
@Getter
@Entity
@Table(name = "`evaluate`")
public class Evaluate extends BaseEntity {

	/** 运单介质 */
	@Transient
	private String name;
	
	public String getNameCN() {
		if (StringUtils.isNotBlank(name)) {
			return DictionaryUtil.getValue(CargoConstant.MEDIUM, name);
		}
		return "";
	}

	/** 价格单位 */
	@Transient
	private String priceUnit;

	public String getPriceUnitCN() {
		if (StringUtils.isNotBlank(priceUnit)) {
			return DictionaryUtil.getValue(CargoConstant.PRICE_UNIT, priceUnit);
		}
		return "";
	}

	/** 运单数量 */
	@Transient
	private Integer qty;

	/** 运单数量单位 */
	@Transient
	private String weightUnit;

	public String getWeightUnitCN() {
		if (StringUtils.isNotBlank(weightUnit)) {
			return DictionaryUtil.getValue(CarConstants.CAR_UNIT, weightUnit);
		}
		return "";
	}

	/** 运单总价 */
	@Transient
	private BigDecimal totalMoney;

	/** 评价人名称 */
	@Transient
	private String appraiserName;

    /** 评价人头像 */
    @Transient
    private String appraiserImage;

	/** 被评价人名称 */
	@Transient
	private String appraiserByName;

	/** 被评价人头像 */
	@Transient
	private String appraiserByImage;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7342312148796379565L;

	/** 主键，自增 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 运单id */
	@Column(name = "`waybill_id`")
	private Integer waybillId;

	/** 运单号 */
	@Column(name = "`bill_no`")
	private String billNo;

	/** 评价人Id */
	@Column(name = "`appraiser_id`")
	private Integer appraiserId;

	/** 被评价人id */
	@Column(name = "`appraiser_by_id`")
	private Integer appraiserById;

	/** 评语 */
	@Column(name = "`content`")
	private String content;

	/** 评价类型 */
	@Column(name = "`evaluate_type`")
	private String evaluateType;

	/** 评价等级 */
	@Column(name = "`evaluate_level`")
	private Integer evaluateLevel;

	/** 创建日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "`create_date`")
	private Date createDate;

	public String getEvaluateTypeCN() {
		if (StringUtils.isNotBlank(evaluateType)) {
			return DictionaryUtil.getValue(CargoConstant.EVALUATE_TYPE, evaluateType);
		}
		return "";
	}

}