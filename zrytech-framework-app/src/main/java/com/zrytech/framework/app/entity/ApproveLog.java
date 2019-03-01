package com.zrytech.framework.app.entity;

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
 * 审核记录
 * 
 * @author cat
 *
 */
@Data
@Entity
@Table(name = "`sys_approve_log`")
public class ApproveLog {
	
	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	/**业务Id*/
	@Column(name = "`business_id`")
    private Integer businessId;

	/**类型*/
	@Column(name = "`approve_type`")
    private String approveType;

	/**申请理由*/
	@Column(name = "`apply_reason`")
    private String applyReason;

	/**审核意见*/
	@Column(name = "`approve_content`")
    private String approveContent;

	/**审核结果*/
	@Column(name = "`approve_result`")
    private String approveResult;

	/**审核Id*/
	@Column(name = "`approve_by`")
    private Integer approveBy;

	/**审核时间*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`approve_time`")
    private Date approveTime;

}