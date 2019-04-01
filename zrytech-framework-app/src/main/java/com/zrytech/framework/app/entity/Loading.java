package com.zrytech.framework.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.utils.DictionaryUtil;
import com.zrytech.framework.base.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Setter
@Getter
@Entity
@Table(name = "`cargo_location`")
@ApiModel(value = "货物装卸地entry")
public class Loading extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1555792098489335740L;

	@ApiModelProperty(value = "主键id", required = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty(value = "货源Id", required = false)
	@Column(name = "`cargo_id`")
	private Integer cargoId;

	@ApiModelProperty(value = "经度", required = false)
	@Column(name = "`longitude`")
	private Double longitude;

	@ApiModelProperty(value = "纬度", required = false)
	@Column(name = "`latitude`")
	private Double latitude;

	@ApiModelProperty(value = "省", required = false)
	@Column(name = "`province`")
	private String province;

	@ApiModelProperty(value = "市", required = false)
	@Column(name = "`city`")
	private String city;

	@ApiModelProperty(value = "县", required = false)
	@Column(name = "`county`")
	private String county;

	@ApiModelProperty(value = "详细地址", required = false)
	@Column(name = "`address_detail`")
	private String addressDetail;

	@ApiModelProperty(value = "装卸数量", required = false)
	@Column(name = "`qty`")
	private Integer qty;

	@ApiModelProperty(value = "类型", required = false)
	@Column(name = "`type`")
	private String type;

	@ApiModelProperty(value = "类型", required = false)
	@Transient
	private String typeCN;

	@ApiModelProperty(value = "备注", required = false)
	@Column(name = "`remark`")
	private String remark;

	@ApiModelProperty(value = "序列", required = false)
	@Column(name = "`seq_no`")
	private Integer seqNo;

	@ApiModelProperty(value = "状态", required = false)
	@Column(name = "`status`")
	private String status;

	@ApiModelProperty(value = "状态", required = false)
	@Transient
	private String statusCN;

	@ApiModelProperty(value = "装卸日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "`load_date`")
	private Date loadDate;

	@ApiModelProperty(value = "截止日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "`end_date`")
	private Date endDate;

	public String getTypeCN() {
		if (!StringUtils.isEmpty(type)) {
			return DictionaryUtil.getValue(CargoConstant.loading_unloading_type, type);
		}
		return typeCN;
	}
	
	@Transient
	private String weightUnit;
	
}
