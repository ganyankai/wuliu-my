package com.zrytech.framework.app.dto.billlocation;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * 新增运单装卸地入参
 * @author cat
 *
 */
@Setter
@Getter
public class BillLocationAddDto {
	
	/**运单装卸Id*/
	private Integer id; // id == null 表示新增，id != null 表示更新
	
	/**经度*/
	@NotNull(message = "经度不能为空")
    private BigDecimal longitude;

	/**纬度*/
	@NotNull(message = "纬度不能为空")
    private BigDecimal latitude;

	/**省份*/
	@NotBlank(message = "省份不能为空")
    private String province;

	/**城市*/
	@NotBlank(message = "城市不能为空")
    private String city;

	/**县*/
	@NotBlank(message = "县不能为空")
    private String county;

	/**地址详情*/
	@NotBlank(message = "地址详情不能为空")
    private String addressDetail;

	/**装卸数量*/
	@NotNull(message = "装卸数量不能为空")
    private Integer qty;

	/**类型*/
    @NotBlank(message = "不能为空")
    private String type;

	/**序号*/
    @NotNull(message = "序号不能为空")
    private Integer seqNo;

	/**说明*/
    private String remark;
    
    /**装卸日期*/
    @NotNull(message = "装卸日期不能为空")
    private Date loadDate;
    
    /**截止日期*/
    @NotNull(message = "截止日期不能为空")
    private Date endDate;

}
