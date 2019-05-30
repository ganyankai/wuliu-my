package com.zrytech.framework.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

/**
 * 车主货主 - 关注线路
 * 
 * @author cat
 *
 */
@Setter
@Getter
@Entity
@Table(name = "`my_focus_line`")
public class MyFocusLine extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5574958022880965129L;

	/** 主键 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 出发省 */
	@Column(name = "`start_province`")
	private String startProvince;

	/** 出发市 */
	@Column(name = "`start_city`")
	private String startCity;

	/** 出发县 */
	@Column(name = "`start_country`")
	private String startCountry;

	/** 达到省 */
	@Column(name = "`end_province`")
	private String endProvince;

	/** 达到市 */
	@Column(name = "`end_city`")
	private String endCity;

	/** 达到县 */
	@Column(name = "`end_country`")
	private String endCountry;

	/** 创建人 */
	@Column(name = "`create_by`")
	private Integer createBy;

	/** 创建日期 */
	@Column(name = "`create_date`")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;

}
