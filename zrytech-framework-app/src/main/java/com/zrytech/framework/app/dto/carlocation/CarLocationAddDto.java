package com.zrytech.framework.app.dto.carlocation;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * 添加车辆位置入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarLocationAddDto {

	/**车牌号*/
	@NotBlank(message = "车牌号不能为空")
    private String carNo;
	
	/**经度*/
	@NotNull(message = "经度不能为空")
    private BigDecimal currentLongitude;

	/**纬度*/
	@NotNull(message = "纬度不能为空")
    private BigDecimal currentLatitude;

	/**当前位置*/
	@NotBlank(message = "当前位置不能为空")
    private String currentAddress;

	/**创建日期*/
	@NotNull(message = "创建日期不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;
}
