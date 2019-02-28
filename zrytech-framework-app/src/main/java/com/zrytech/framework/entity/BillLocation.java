package com.zrytech.framework.entity;

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
 * 运单装卸地
 *
 */
@Data
@Entity
@Table(name = "`bill_location`")
public class BillLocation {
	
	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	/**运单明细ID*/
	@Column(name = "`waybill_detail_id`")
    private Integer waybillDetailId;

	/**运单Id*/
	@Column(name = "`waybill_id`")
    private Integer waybillId;

	/**经度*/
	@Column(name = "`longitude`")
    private BigDecimal longitude;

	/**纬度*/
	@Column(name = "`latitude`")
    private BigDecimal latitude;

	/**省份*/
	@Column(name = "`province`")
    private String province;

	/**城市*/
	@Column(name = "`city`")
    private String city;

	/**县*/
	@Column(name = "`county`")
    private String county;

	/**地址详情*/
	@Column(name = "`address_detail`")
    private String addressDetail;

	/**装卸数量*/
	@Column(name = "`qty`")
    private Integer qty;

	/**重量单位*/
	@Column(name = "`weight_unit`")
    private String weightUnit;

	/**类型*/
	@Column(name = "`type`")
    private String type;

	/**序号*/
	@Column(name = "`seq_no`")
    private Integer seqNo;

	/**说明*/
	@Column(name = "`remark`")
    private String remark;

	/**装卸日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`load_date`")
    private Date loadDate;

	/**截止日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`end_date`")
    private Date endDate;

	/**状态*/
	@Column(name = "`status`")
    private String status;

    /**创建日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`create_date`")
	private Date createDate;

}