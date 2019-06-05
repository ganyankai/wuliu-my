package com.zrytech.framework.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 热门货源地
 * 
 * @author dante
 *
 */
@Setter
@Getter
@Entity
@Table(name = "`hot_place`")
public class HotPlace extends BaseEntity {

	private static final long serialVersionUID = 5574958022880965129L;

	/** 主键 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 省份 */
	@Column(name = "`province`")
	private String province;

	/** 城市 */
	@Column(name = "`city`")
	private String city;

	/** 县 */
	@Column(name = "`country`")
	private String country;


	/** 创建日期 */
	@Column(name = "`create_date`")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;

}
