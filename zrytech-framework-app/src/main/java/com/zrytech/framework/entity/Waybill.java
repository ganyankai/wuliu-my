package com.zrytech.framework.entity;

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

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 运单
 *
 */
@Data
@Entity
@Table(name = "`waybill`")
public class Waybill {
	
	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	/**订单编号*/
	@Column(name = "`bill_no`")
    private String billNo;

	/**货源Id*/
	@Column(name = "`cargo_id`")
    private Integer cargoId;
	
	/**货源*/
	@Transient
	private Cargo cargo;

	/**货主Id*/
	@Column(name = "`cargo_ownner_id`")
    private Integer cargoOwnnerId;
	
	/**货主企业名称*/
	@Transient
	private String cargoOwnerName;

	/**车主Id*/
	@Column(name = "`car_ownner_id`")
    private Integer carOwnnerId;

	/**车主企业名称*/
	@Transient
	private String carOwnerName;
	
	/**预付款*/
	@Column(name = "`advance_moeny`")
    private BigDecimal advanceMoeny;

	/**尾款*/
	@Column(name = "`final_money`")
    private BigDecimal finalMoney;

	/**总金额*/
	@Column(name = "`total_money`")
    private BigDecimal totalMoney;

	/**支付类型*/
	@Column(name = "`pay_type`")
    private String payType;
	
	/**支付类型*/
	@Transient
	private String payTypeCN;		// TODO
	
	/**付款方式*/
	@Column(name = "`pay_way`")
    private String payWay;
	
	/**付款方式*/
	@Transient
	private String payWayCN;		// TODO

	/**备注*/
	@Column(name = "`remark`")
    private String remark;

	/**数量*/
	@Column(name = "`qty`")
    private Integer qty;

	/**重量单位*/
	@Column(name = "`weight_unit`")
    private String weightUnit;

	/**运单类型*/
	@Column(name = "`bill_type`")
    private String billType;
	
	/**运单类型*/
	@Transient
	private String billTypeCN;		// TODO

	/**收货凭证*/
	@Column(name = "`proof_imgs`")
    private String proofImgs;

	/**状态*/
	@Column(name = "`status`")
    private String status;

	/**状态*/
	@Transient
	private String statusCN;		// TODO
	
    /**创建日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`create_date`")
    private Date createDate;
	
	/**运单项*/
	@Transient
	private List<WaybillDetail> waybillDetails;
	
	/**运单评价*/
	@Transient
	private List<Evaluate> evaluates;
}