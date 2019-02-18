package com.zry.framework.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
@Entity
@Table(name = "`sys_customer`")
public class Customer {
	
	/**主键，自增*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	/**账号*/
	@Column(name = "`login_counter`")
    private String loginCounter;

	/**手机号*/
	@Column(name = "`tel`")
	private String tel;

	/**密码*/
	@Column(name = "`pwd`")
	private String pwd;

	/**名称*/
	@Column(name = "`name`")
	private String name;

	/**logo*/
	@Column(name = "`logo`")
	private String logo;

	/**appOpenid*/
	@Column(name = "`app_openid`")
	private String appOpenid;

	/**openid*/
	@Column(name = "`openid`")
	private String openid;

	/**unionid*/
	@Column(name = "`unionid`")
	private String unionid;

	/**推荐人*/
	@Column(name = "`referrer`")
	private Integer referrer;

	/**用户类型*/
	@Column(name = "`customer_type`")
	private String customerType;

	/**状态*/
	@Column(name = "`is_active`")
	private Boolean isActive;

	/**创建人*/
	@Column(name = "`create_by`")
	private Integer createBy;

	/**注册日期*/
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "`create_date`")
	private Date createDate;

    
}