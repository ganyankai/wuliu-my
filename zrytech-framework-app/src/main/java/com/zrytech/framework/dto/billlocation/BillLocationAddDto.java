package com.zrytech.framework.dto.billlocation;

import java.math.BigDecimal;
import java.util.Date;

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
    private BigDecimal longitude;

	/**纬度*/
    private BigDecimal latitude;

	/**省份*/
    private String province;

	/**城市*/
    private String city;

	/**县*/
    private String county;

	/**地址详情*/
    private String addressDetail;

	/**装卸数量*/
    private Integer qty;

	/**类型*/
    private String type;

	/**序号*/
    private Integer seqNo;

	/**说明*/
    private String remark;
    
    /**装卸日期*/
    private Date loadDate;
    
    /**截止日期*/
    private Date endDate;

}
