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

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


/**
 * 报价单
 *
 */
@Data
@Entity
@Table(name = "`cargo_matter`")
public class CargoMatter {
	
	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	/**货源ID*/
	@Column(name = "`cargo_id`")
    private Integer cargoId;
	
	/**货源*/
	@Transient
	private Cargo cargo;
	
	/**创建人Id*/
	@Column(name = "`create_by`")
	private Integer createBy;
	
	/**车主Id*/
	@Column(name = "`car_ownner_id`")
    private Integer carOwnnerId;

	/**车主企业名称*/
	@Transient
	private String carOwnerName;
	
	/**应标价格*/
	@Column(name = "`matter_price`")
    private BigDecimal matterPrice;

	/**状态*/
	@Column(name = "`status`")
    private String status;
	
	/**状态*/
	@Transient
	private String statusCN;
	
	/**状态*/
	public String getStatusCN() {
		return "待处理";
	}

	/**中标时间*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`load_date`")
    private Date loadDate;

    /**创建日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`create_date`")
    private Date createDate;
	
	
	/**货主企业名称*/
	@Transient
	private String cargoOwnerName;
	
   
}