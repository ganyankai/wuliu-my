package com.zrytech.framework.app.dto.carsourcecar;

import lombok.Getter;
import lombok.Setter;

/**
 * 车源之新增车辆或更新车辆入参
 * <p>id为空表示新增，id不为空表示更新</p>
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourceCarUpdateDto {
	
	/**车源车辆关系Id*/
	private Integer id;
	
	/**车辆Id*/
    private Integer carId;

	/**司机Id*/
    private Integer driverId;

	/**压货人Id*/
    private Integer supercargoId;
}
