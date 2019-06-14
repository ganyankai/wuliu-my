package com.zrytech.framework.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.app.constants.CarConstants;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.utils.DictionaryUtil;
import com.zrytech.framework.base.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

import org.springframework.util.StringUtils;

/**
 * 运单项
 */
@Setter
@Getter
@Entity
@Table(name = "`waybill_detail`")
public class WaybillDetail extends BaseEntity {
	
	/** 车牌号 */
	@Transient
	private String carNo;
	
	/** 司机姓名 */
	@Transient
	private String driverName;
	
	/** 司机身份证 */
	@Transient
	private String driverIdCard;
	
	/** 司机电话 */
	@Transient
	private String driverTel;
	
	/** 压货人姓名 */
	@Transient
	private String supercargoName;
	
	/** 压货人身份证 */
	@Transient
	private String supercargoIdCard;
	
	/** 压货人电话 */
	@Transient
	private String supercargoTel;
	
	/**
	 * 运单装卸地
	 */
	@Transient
	private List<BillLocation> billLocations;

	/**
	 * 运单运输介质
	 */
	@Transient
	private String waybillName;

	/**
	 * 运单Id
	 */
	@Column(name = "`waybill_id`")
	private Integer waybillId;


	/**
	 * 
	 */
	private static final long serialVersionUID = -4998910874108455575L;

	/**
	 * 主键，自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 订单编号
	 */
	@Column(name = "`bill_no`")
	private String billNo;

	/**
	 * 车辆Id
	 */
	@Column(name = "`car_id`")
	private Integer carId;

	/**
	 * 司机Id
	 */
	@Column(name = "`driver_id`")
	private Integer driverId;

	/**
	 * 压货人Id
	 */
	@Column(name = "`supercargo_id`")
	private Integer supercargoId;

	/**
	 * 运输数量
	 */
	@Column(name = "`qty`")
	private Integer qty;

	/**
	 * 重量单位
	 */
	@Column(name = "`weight_unit`")
	private String weightUnit;

	/**
	 * 待确认，待发货，待收货
	 */
	@Column(name = "`flow_name`")
	private String flowName;

	/**
	 * 创建日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "`create_date`")
	private Date createDate;

	public String getWeightUnitCN() {
		if (!StringUtils.isEmpty(weightUnit)) {
			return DictionaryUtil.getValue(CarConstants.CAR_UNIT, weightUnit);
		}
		return "";
	}

	public String getFlowNameCN() {
		if (!StringUtils.isEmpty(flowName)) {
			return DictionaryUtil.getValue(CargoConstant.FLOW_STATUS, flowName);
		}
		return "";
	}

	public String getWaybillNameCN() {
		if (!StringUtils.isEmpty(waybillName)) {
			return DictionaryUtil.getValue(CargoConstant.MEDIUM, waybillName);
		}
		return "";
	}
}