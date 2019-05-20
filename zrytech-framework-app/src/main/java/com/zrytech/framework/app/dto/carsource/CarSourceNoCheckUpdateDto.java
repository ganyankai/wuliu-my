package com.zrytech.framework.app.dto.carsource;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * 修改车源基本信息入参（不需要审核的内容）
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourceNoCheckUpdateDto {

	/** 车源Id */
	@NotNull(message = "车源Id不能为空")
	private Integer id;

	/** 发车时间 */
	@JsonFormat(pattern = "yyyy-MM-dd a", timezone = "GMT+8")
	private Date startDate;

	/** 是否拼车 */
	@NotNull(message = "是否拼车不能为空")
	private Boolean isShare;

	/** 备注 */
	private String remark;
	
}
