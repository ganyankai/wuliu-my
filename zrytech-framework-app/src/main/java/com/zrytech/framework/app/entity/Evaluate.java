package com.zrytech.framework.app.entity;

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
import com.zrytech.framework.base.entity.BaseEntity;

import lombok.Data;

/**
 * 评论
 * @author cat
 *
 */
@Data
@Entity
@Table(name = "`evaluate`")
public class Evaluate extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7342312148796379565L;

	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	/**运单号*/
	@Column(name = "`bill_no`")
    private String billNo;

	/**评价人Id*/
	@Column(name = "`appraiser_id`")
    private Integer appraiserId;

	/**被评价人id*/
	@Column(name = "`appraiser_by_id`")
    private Integer appraiserById;

	/**评语*/
	@Column(name = "`content`")
    private String content;

	/**评价类型*/
	@Column(name = "`evaluate_type`")
    private String evaluateType;
	
	/***/
	@Transient
	private String evaluateTypeCN;

	/**评价等级*/
	@Column(name = "`evaluate_level`")
    private Integer evaluateLevel;

	/**创建日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`create_date`")
    private Date createDate;

}