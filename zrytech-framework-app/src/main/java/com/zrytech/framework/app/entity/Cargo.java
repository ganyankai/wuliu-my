package com.zrytech.framework.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.app.constants.CarConstants;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.utils.DictionaryUtil;
import com.zrytech.framework.base.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

/**
 * 货源
 */
@Setter
@Getter
@Entity
@Table(name = "`cargo`")
public class Cargo extends BaseEntity implements Serializable {
	
	/** 平均评论等级 */
	@Transient
	private BigDecimal levelAVG;
	
	/** 是否已报价 */
	@Transient
	private Boolean isOffer;
	
	/** 是否已关注 */
	@Transient
	private Boolean isFocus;

	/** 货源装卸地 */
	@Transient
	private List<Loading> cargoLocations;
	
	/** 货主企业名称 */
	@Transient
	private String cargoOwnerName;
	
	/** 货主联系电话 */
	@Transient
	private String cargoOwnerTel;
	
	/** 报价单总数 */
	@Transient
	private Integer cargoMatterCount;
	
	/** 中标的报价单 */
	@Transient
	private CargoMatter cargoMatter;
	
	/** 货源的所有报价单 */
	@Transient
	private List<CargoMatter> cargoMatters;

	private static final long serialVersionUID = -1555792098489335740L;

	/**
	 * 主键，自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 货物介质 */
	@Column(name = "`name`")
	private String name;

	/** 货物logo */
	@Column(name = "`logo`")
	private String logo;

	/** 货物数量 */
	@Column(name = "`qty`")
	private Integer qty;

	/** 是否多点装货 */
	@Column
	private Boolean mulShipment;

	/** 是否多点卸货 */
	@Column
	private Boolean mulUnload;

	/** 是否有包装 */
	@Column
	private Boolean packaged;

	/** 发标价 */
	@Column
	private BigDecimal matterPrice;

	/** 中标价 */
	@Column
	private BigDecimal realPrice;

	/** 市场价 */
	@Column
	private BigDecimal marketPrice;

	/** 出发省 */
	@Column
	private String startProvince;

	/** 出发市 */
	@Column
	private String startCity;

	/** 出发县 */
	@Column
	private String startCountry;

	/** 达到省 */
	@Column
	private String endProvince;

	/** 达到市 */
	@Column
	private String endCity;

	/** 达到县 */
	@Column
	private String endCountry;

	/** 路线 */
	@Column
	private String line;

	/** 是否拼单 */
	@Column
	private Boolean canShare;

	/** 送达日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column
	private Date arrivalDate;

	/** 提货日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column
	private Date pickupDate;

	/** 截止日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column
	private Date endDate;

	/** 备注 */
	@Column
	private String remark;

	/** 创建人 */
	@Column
	private Integer createBy;

	/** 创建日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column
	private Date createDate;

	/** 重量单位 */
	@Column(name = "`weight_unit`")
	private String weightUnit;

	/** 发标方式 */
	@Column(name = "`tender_way`")
	private String tenderWay;

	/** 付款方式 */
	@Column
	private String payType;

	/** 价格单位 */
	@Column
	private String priceUnit;

	/** 价格类型 */
	@Column
	private String priceType;

	/** 车辆类型 */
	@Column
	private String carType;

	/** 状态 */
	@Column(name = "`status`")
	private String status;

	public String getWeightUnitCN() {
		if (StringUtils.isNotBlank(weightUnit)) {
			return DictionaryUtil.getValue(CarConstants.CAR_UNIT, weightUnit);
		}
		return "";
	}

	public String getTenderWayCN() {
		if (StringUtils.isNotBlank(tenderWay)) {
			return DictionaryUtil.getValue(CargoConstant.PUSH_MARK_WAY, tenderWay);
		}
		return "";
	}

	public String getPayTypeCN() {
		if (StringUtils.isNotBlank(payType)) {
			return DictionaryUtil.getValue(CargoConstant.PAY_TYPE, payType);
		}
		return "";
	}

	public String getPriceUnitCN() {
		if (StringUtils.isNotBlank(priceUnit)) {
			return DictionaryUtil.getValue(CargoConstant.PRICE_UNIT, priceUnit);
		}
		return "";
	}

	public String getPriceTypeCN() {
		if (StringUtils.isNotBlank(priceType)) {
			return DictionaryUtil.getValue(CargoConstant.PRICE_TYPE, priceType);
		}
		return "";
	}

	public String getCarTypeCN() {
		if (StringUtils.isNotBlank(carType)) {
			return DictionaryUtil.getValue(CarConstants.CAR_TYPE, carType);
		}
		return "";
	}

	public String getStatusCN() {
		if (StringUtils.isNotBlank(status)) {
			return DictionaryUtil.getValue(CargoConstant.CARGO_SOURCE_STATUS, status);
		}
		return "";
	}
	
	public String getNameCN() {
		if (StringUtils.isNotBlank(name)) {
			return DictionaryUtil.getValue(CargoConstant.MEDIUM, name);
		}
		return "";
	}

}
