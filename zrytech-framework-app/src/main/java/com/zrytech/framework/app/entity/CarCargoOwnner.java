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
import com.zrytech.framework.app.constants.CarCargoOwnerConstants;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerNeedApproveDto;
import com.zrytech.framework.app.utils.DictionaryUtil;

import lombok.Data;

/**
 * 车主与货主
 */
@Data
@Entity
@Table(name = "`car_cargo_ownner`")
public class CarCargoOwnner {
	
	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**企业名称*/
	@Column(name = "`name`")
    private String name;

    /**信用代码*/
	@Column(name = "`credit_code`")
    private String creditCode;

    /**营业执照*/
	@Column(name = "`business_license`")
    private String businessLicense;

    /**法人姓名*/
	@Column(name = "`legaler_name`")
	private String legalerName;

    /**法人身份证号码*/
	@Column(name = "`legaler_id_card_no`")
	private String legalerIdCardNo;

    /**法人身份证照片*/
	@Column(name = "`legaler_id_card_front`")
    private String legalerIdCardFront;

    /**联系电话*/
	@Column(name = "`tel`")
	private String tel;

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

    /**企业简介*/
	@Column(name = "`intro`")
	private String intro;

    /**类型*/
	@Column(name = "`customer_type`")
    private String customerType;
	
    public String getCustomerTypeCN() {
    	if (StringUtils.isNotBlank(customerType)) {
            return DictionaryUtil.getValue(CarCargoOwnerConstants.CUSTOMER_TYPE, customerType);
        }
        return customerType;
    }
    
    /**免审核*/
    @Column(name = "`avoid_audit`")
    private Boolean avoidAudit;

    /**成交率*/
    @Column(name = "`close_rate`")
    private BigDecimal closeRate;

    /**好评等级*/
    @Column(name = "`favorable_level`")
    private Integer favorableLevel;

    /**状态*/
    @Column(name = "`status`")
    private String status;

    public String getStatusCN() {
    	if (StringUtils.isNotBlank(status)) {
            return DictionaryUtil.getValue(CarCargoOwnerConstants.STATUS, status);
        }
        return status;
    }
    
    /**客户Id*/
    @Column(name = "`cusomer_id`")
    private Integer customerId;
    
    /**推荐人Id*/
    @Column(name = "`referees_id`")
    private Integer refereesId;
    
    /**客户*/
    @Transient
    private Customer customer;
    
    /**创建日期*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "`create_date`")
    private Date createDate;
    
    /**审批状态*/
    @Column(name = "`approve_status`")
    private String approveStatus;
    
    public String getApproveStatusCN() {
    	if (StringUtils.isNotBlank(approveStatus)) {
            return DictionaryUtil.getValue(ApproveConstants.STATUS, approveStatus);
        }
        return approveStatus;
    }
    
    /**需要审批字段的JSON字符串*/
    @JsonIgnore
    @Column(name = "`approve_content`")
    private String approveContent;
    
    @Transient
    private CarCargoOwnerNeedApproveDto approveContentCN;
    
    /**类型*/
    @Column(name = "`type`")
    private String type;
    
    public String getTypeCN() {
    	if (StringUtils.isNotBlank(type)) {
            return DictionaryUtil.getValue(CarCargoOwnerConstants.TYPE, type);
        }
        return type;
    }
    
}