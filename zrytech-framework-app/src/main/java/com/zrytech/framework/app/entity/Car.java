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

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.CarConstants;
import com.zrytech.framework.app.dto.car.CarCheckUpdateDto;
import com.zrytech.framework.app.utils.DictionaryUtil;
import com.zrytech.framework.base.entity.BaseEntity;

import lombok.Data;

/**
 * 车辆
 * @author cat
 */
@Data
@Entity
@Table(name = "`car`")
public class Car extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1763351958891301342L;

	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	/**车牌号*/
	@Column(name = "`car_no`")
    private String carNo;

	/**车载量*/
	@Column(name = "`car_load`")
    private Integer carLoad;

	/**车载量单位*/
	@Column(name = "`car_unit`")
    private String carUnit;

	/**车辆类型*/
	@Column(name = "`car_type`")
    private String carType;
	
	/**司机Id*/
	@Column(name = "`driver_id`")
    private Integer driverId;

	/**押货人Id*/
	@Column(name = "`supercargo_id`")
    private Integer supercargoId;

	/**经度*/
	@Column(name = "`current_longitude`")
    private BigDecimal currentLongitude;

	/**纬度*/
	@Column(name = "`current_latitude`")
    private BigDecimal currentLatitude;

	/**当前位置*/
	@Column(name = "`current_address`")
    private String currentAddress;

	/**是否分仓*/
	@Column(name = "`mul_store`")
    private Boolean mulStore;

	/**仓位数量*/
	@Column(name = "`store_qty`")
    private Integer storeQty;

	/**车辆状态*/
	@Column(name = "`status`")
    private String status;
	
	/**是否删除*/
	@Column(name = "`is_delete`")
    private Boolean isDelete;

	/**车主Id*/
	@Column(name = "`create_by`")
    private Integer createBy;

	/**创建日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`create_date`")
    private Date createDate;
	
	/**车主Id*/
	@Column(name = "`car_owner_id`")
	private Integer carOwnerId;

	/**车长*/
	@Column(name = "`car_length`")
	private String carLength;


	/**车主企业名称*/
	@Transient
	private String carOwnerName;
	
	/**车主*/
	@Transient
	private CarCargoOwnner carOwner;
	
	/**司机姓名*/
	@Transient
	private String driverName;
	
	/**司机*/
	@Transient
	private CarPerson driver;
	
	/**压货人姓名*/
	@Transient
	private String supercargoName;
	
	/**压货人*/
	@Transient
	private CarPerson supercargo;
	
	public String getCarUnitCN() {
		if (StringUtils.isNotBlank(carUnit)) {
            return DictionaryUtil.getValue(CarConstants.CAR_UNIT, carUnit);
        }
        return carUnit;
	}
	
	public String getCarTypeCN() {
		if (StringUtils.isNotBlank(carType)) {
            return DictionaryUtil.getValue(CarConstants.CAR_TYPE, carType);
        }
        return carType;
	}
	
	public String getStatusCN() {
		if (StringUtils.isNotBlank(status)) {
            return DictionaryUtil.getValue(CarConstants.CAR_STATUS, status);
        }
        return status;
	}
	
    /**审批状态*/
    @Column(name = "`approve_status`")
    private String approveStatus;
    
    /**需要审批字段的JSON字符串*/
    @JsonIgnore
    @Column(name = "`approve_content`")
    private String approveContent;
	
    public String getApproveStatusCN() {
    	if (StringUtils.isNotBlank(approveStatus)) {
            return DictionaryUtil.getValue(ApproveConstants.STATUS, approveStatus);
        }
        return approveStatus;
    }
    
    @Transient
    private CarCheckUpdateDto approveContentCN;

	public String getCarLengthCN() {
		if (StringUtils.isNotBlank(carLength)) {
			return DictionaryUtil.getValue(CarConstants.CAR_LENGTH, carLength);
		}
		return carLength+" meter";
	}
}