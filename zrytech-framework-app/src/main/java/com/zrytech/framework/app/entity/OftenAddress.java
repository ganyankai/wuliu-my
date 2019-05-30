package com.zrytech.framework.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.utils.DictionaryUtil;
import com.zrytech.framework.base.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * 常用地址
 */
@Setter
@Getter
@Entity
@Table(name = "`address`")
public class OftenAddress extends BaseEntity {

	private static final long serialVersionUID = -1555792098489335740L;

	/** 主键id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 客户id */
	@Column(name = "`customer_id`")
	private Integer customerId;

	/** 货物介质 */
	@Column(name = "`cargo_name`")
	private String cargoName;

	/** 出发省份 */
	@Column(name = "`begin_province`")
	private String beginProvince;

	/** 出发城市 */
	@Column(name = "`begin_city`")
	private String beginCity;

	/** 出发县 */
	@Column(name = "`begin_county`")
	private String beginCounty;

	/** 到达省份 */
	@Column(name = "`end_province`")
	private String endProvince;

	/** 到达城市 */
	@Column(name = "`end_city`")
	private String endCity;

	/** 到达县 */
	@Column(name = "`end_county`")
	private String endCounty;

	/** 创建日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "`create_date`")
	private Date createDate;

	public String getNameCN() {
		if (StringUtils.isNotBlank(cargoName)) {
			return DictionaryUtil.getValue(CargoConstant.MEDIUM, cargoName);
		}
		return "";
	}
}
