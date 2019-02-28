package com.zrytech.framework.dto.carsourcecar;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 车源之新增车辆或更新车辆入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourceCarSaveDto {
	
	/**车源Id*/
	@NotNull(message= "车源Id不能为空")
	private Integer carSourceId;
	
	/**车辆*/
    private List<CarSourceCarUpdateDto> carSourceCars;
}
