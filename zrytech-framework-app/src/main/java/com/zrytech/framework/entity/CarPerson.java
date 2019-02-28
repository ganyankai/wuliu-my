package com.zrytech.framework.entity;

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
import com.zrytech.framework.constants.CarPersonConstants;

import lombok.Data;

/**
 * 车辆司机与压货人
 * @author cat
 */
@Data
@Entity
@Table(name = "`car_person`")
public class CarPerson {
	
	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	/**姓名*/
	@Column(name = "`name`")
    private String name;

	/**logo*/
	@Column(name = "`logo`")
    private String logo;
	
	/**手机号*/
	@Column(name = "`tel`")
    private String tel;
	
	/**性别*/
	@Column(name = "`sex`")
    private String sex;

	/**年龄*/
	@Column(name = "`age`")
    private Integer age;
	
	/**身份证*/
	@Column(name = "`id_card`")
    private String idCard;

	/**类型*/
	@Column(name = "`person_type`")
    private String personType;
	
	/**类型*/
	@Transient
	private String personTypeCN;
	
	/**类型*/
	public String getPersonTypeCN() {
		if(CarPersonConstants.PERSON_TYPE_DRIVER.equals(personType)) {
			return "司机";
		}else if(CarPersonConstants.PERSON_TYPE_SUPERCARGO.equals(personType)) {
			return "押货人";
		}else {
			return "";
		}
	}
	
	/**客户Id*/
	@Column(name = "`customer_id`")
    private Integer customerId;
	
	/**驾驶证*/
	@Column(name = "`driving_licence`")
    private String drivingLicence;
	
	/**状态*/
	@Column(name = "`status`")
    private String status;
	
	/**状态*/
	@Transient
	private String statusCN;
	
	/**状态*/
	public String getStatusCN() {
		return "状态：待处理";// TODO
	}
	
	/**删除标识*/
	@Column(name = "`is_delete`")
    private Boolean isDelete;
	
	/**创建人Id*/
	@Column(name = "`create_by`")
    private Integer createBy;

    /**创建日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`create_date`")
    private Date createDate;
	
	/**车主企业名称*/
	@Transient
	private String carOwnerName;
	
	/**车主*/
	@Transient
	private CarCargoOwnner carOwner;
	
	/**车主Id*/
	@Column(name = "`car_owner_id`")
	private Integer carOwnerId;
	
}