package com.zrytech.framework.app.dto.carsource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceAddDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarAddDto;

import lombok.Getter;
import lombok.Setter;

/**
 * 新增车源入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourceAddDto {
	
	/**车辆类型*/
	@NotBlank(message = "车辆类型不能为空")
    private String carType;
	
	/**核载量*/
    @NotNull(message = "核载量不能为空")
    private Integer qty;
    
    /**车源的路线*/
    @Valid
    List<CarRecordPlaceAddDto> carRecordPlaces;
    
    /**车源的车辆司机压货人*/
    @Valid
    List<CarSourceCarAddDto> carSourceCars;

}


