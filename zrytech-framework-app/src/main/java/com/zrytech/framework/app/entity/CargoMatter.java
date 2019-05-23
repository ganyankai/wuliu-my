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
import com.zrytech.framework.app.constants.CargoMatterConstants;
import com.zrytech.framework.app.utils.DictionaryUtil;
import com.zrytech.framework.base.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 报价单
 * 
 * <pre>
 * 1,仅招标，邀标的货源可以报价。
 * 2,招标，邀标的运单价格以报价单的价格为准。
 * </pre>
 */
@Setter
@Getter
@Entity
@Table(name = "`cargo_matter`")
public class CargoMatter extends BaseEntity {

	/** 货源 */
	@Transient
	private Cargo cargo;

	/** 车主企业名称 */
	@Transient
	private String carOwnerName;

	/** 货主企业名称 */
	@Transient
	private String cargoOwnerName;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1682998198356895447L;

	/**
	 * 单价
	 * <p>
	 * 比如货源是10吨货物，定义单价100元/吨，定义总价900元。那么按照单价计算的总价是1000元，而实际定义的总价是900元，可以看出优惠了100元。
	 * 最终运单价格以 matterPrice 的价格为准。
	 * </p>
	 */
	@Column(name = "`unit_price`")
	private BigDecimal unitPrice;

	/** 主键，自增 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 货源Id */
	@Column(name = "`cargo_id`")
	private Integer cargoId;

	/** 创建人Id */
	@Column(name = "`create_by`")
	private Integer createBy;

	/** 车主Id */
	@Column(name = "`car_ownner_id`")
	private Integer carOwnnerId;

	/** 货主Id */
	@Column(name = "`cargo_owner_id`")
	private Integer cargoOwnerId;

	/** 应标价格 */
	@Column(name = "`matter_price`")
	private BigDecimal matterPrice;

	/** 状态 */
	@Column(name = "`status`")
	private String status;

	/** 中标时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "`load_date`")
	private Date loadDate;

	/** 创建日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "`create_date`")
	private Date createDate;

	/** 状态CN */
	public String getStatusCN() {
		if (StringUtils.isNotBlank(status)) {
			return DictionaryUtil.getValue(CargoMatterConstants.CARGO_MATTER_STATUS, status);
		}
		return "";
	}

}