package com.zrytech.framework.app.entity;

import com.aliyuncs.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.utils.DictionaryUtil;
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
import javax.persistence.Transient;


/**
 * 车主货主关注
 */
@Setter
@Getter
@Entity
@Table(name = "`my_focus_person`")
public class MyFocusPerson extends BaseEntity {
	
	/** 被关注人的名称 */
	@Transient
	private String beFocuserName;

	/**
	 * 
	 */
	private static final long serialVersionUID = -462821343490348278L;

	/** 主键 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 关注人id */
	@Column(name = "`focuser_id`")
	private Integer focuserId;

	/** 被关注人id */
	@Column(name = "`be_focuser_id`")
	private Integer beFocuserId;

	/** 关注类型 */
	@Column(name = "`focus_type`")
	private String focusType;

	/** 创建日期 */
	@Column(name = "`create_date`")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;

	public String getFocusTypeCN() {
		if (StringUtils.isNotEmpty(focusType)) {
			return DictionaryUtil.getValue(CargoConstant.MY_FOCUS_PERSON_TYPE, focusType);
		}
		return "";
	}

}
