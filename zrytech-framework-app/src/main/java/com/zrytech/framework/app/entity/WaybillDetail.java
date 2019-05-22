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

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.app.constants.CarConstants;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.utils.DictionaryUtil;
import com.zrytech.framework.base.entity.BaseEntity;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 运单项
 */
@Data
@Entity
@Table(name = "`waybill_detail`")
public class WaybillDetail extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4998910874108455575L;

	/**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单编号
     */
    @Column(name = "`bill_no`")
    private String billNo;

    /**
     * 车辆Id
     */
    @Column(name = "`car_id`")
    private Integer carId;

    /**
     * 司机Id
     */
    @Column(name = "`driver_id`")
    private Integer driverId;

    /**
     * 压货人Id
     */
    @Column(name = "`supercargo_id`")
    private Integer supercargoId;

    /**
     * 运输数量
     */
    @Column(name = "`qty`")
    private Integer qty;

    /**
     * 重量单位
     */
    @Column(name = "`weight_unit`")
    private String weightUnit;

    /**
     * 重量单位
     */
    @Transient
    private String weightUnitCN;

    /**
     * 待确认，待发货，待收货
     */
    @Column(name = "`flow_name`")
    private String flowName;

    /**
     * 待确认，待发货，待收货
     */
    @Transient
    private String flowNameCN;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "`create_date`")
    private Date createDate;

    /**
     * 运单装卸地
     */
    @Transient
    private List<BillLocation> billLocations;

    public String getWeightUnitCN() {
        if (!StringUtils.isEmpty(weightUnit)) {
            return DictionaryUtil.getValue(CarConstants.CAR_UNIT, weightUnit);
        }
        return weightUnitCN;
    }

    public String getFlowNameCN() {
        if (!StringUtils.isEmpty(flowName)) {
            return DictionaryUtil.getValue(CargoConstant.FLOW_STATUS, flowName);
        }
        return flowNameCN;
    }
}