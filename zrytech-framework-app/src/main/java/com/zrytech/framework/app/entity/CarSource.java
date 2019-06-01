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

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.CarConstants;
import com.zrytech.framework.app.constants.CarSourceConstants;
import com.zrytech.framework.app.dto.carsource.CarSourceCheckUpdateDto;
import com.zrytech.framework.app.utils.DictionaryUtil;
import com.zrytech.framework.base.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;


/**
 * 车源
 *
 */
@Setter
@Getter
@Entity
@Table(name = "`car_source`")
public class CarSource extends BaseEntity {
	
	/** 货主是否已关注车主 */
	@Transient
	private Boolean isFocus;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6093142606960464583L;

	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	/**创建人Id*/
	@Column(name = "`create_by`")
    private Integer createBy;
	
	/**创建人名称*/
	@Transient
	private String userName;
	
	/**车主Id*/
	@Column(name = "`car_owner_id`")
    private Integer carOwnerId;
	
	/**车主企业名称*/
	@Transient
	private String carOwnerName;

	/**车辆类型*/
	@Column(name = "`car_type`")
    private String carType;
	
	/**车辆类型*/
	public String getCarTypeCN() {
		if (StringUtils.isNotBlank(carType)) {
            return DictionaryUtil.getValue(CarConstants.CAR_TYPE, carType);
        }
        return carType;
	}
	
	/**运输量*/
	@Column(name = "`qty`")
    private Integer qty;
	
	/**运输量单位*/
	@Column(name = "`unit`")
	private String unit;

	/**运输量单位*/
	public String getUnitCN() {
		if (StringUtils.isNotBlank(unit)) {
            return DictionaryUtil.getValue(CarConstants.CAR_UNIT, unit);
        }
        return unit;
	}
	
	/**状态*/
	@Column(name = "`status`")
    private String status;
	
	/**状态*/
	public String getStatusCN() {
		if (StringUtils.isNotBlank(status)) {
            return DictionaryUtil.getValue(CarSourceConstants.CAR_SOURCE_STATUS, status);
        }
        return status;
	}
	
    /**创建日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`create_date`")
    private Date createDate;
	
	/**车源路线列表*/
	@Transient
	private List<CarRecordPlace> carRecordPlaces;
	
	/**车源车辆列表*/
	@Transient
	private List<CarSourceCar> carSourceCars;
	
	
	/**审批状态*/
	@Column(name = "`approve_status`")
	private String approveStatus;
	
	public String getApproveStatusCN() {
		if (StringUtils.isNotBlank(approveStatus)) {
			return DictionaryUtil.getValue(ApproveConstants.STATUS, approveStatus);
		}
		return approveStatus;
	}

	/**需要审批的内容JSON*/
	@JsonIgnore
	@Column(name = "`approve_content`")
	private String approveContent;
	
	@Transient
	private CarSourceCheckUpdateDto approveContentCN;
	
	
	// 2019-5-20 13:53:59 新增字段
	
	/** 发车时间 */
	@Column(name = "`start_date`")
	@JsonFormat(pattern = "yyyy-MM-dd a", timezone = "GMT+8")
	private Date startDate;
	
	/** 是否拼车 */
	@Column(name = "`is_share`")
	private Boolean isShare;
	
	/** 备注 */
	@Column(name = "`remark`")
	private String remark;
	
}