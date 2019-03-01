package com.zrytech.framework.app.dto.carsourcecar;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 车源之新增或更新车辆入参
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
	@NotNull(message= "车辆不能为空")
    private List<CarSourceCarUpdateDto> carSourceCars;
}
