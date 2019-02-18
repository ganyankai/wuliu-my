package com.zry.framework.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	/***/
	@Column(name = "`cargo_id`")
    private Integer cargoId;

	/***/
	@Column(name = "`car_ownner_id`")
    private Integer carOwnnerId;

	/***/
	@Column(name = "`matter_price`")
    private BigDecimal matterPrice;

	/***/
	@Column(name = "`status`")
    private String status;

	/***/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`load_date`")
    private Date loadDate;

    /**创建日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`create_date`")
    private Date createDate;
   
}