package com.zrytech.framework.dto.carsource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.zrytech.framework.dto.carrecordplace.CarRecordPlaceAddDto;
import com.zrytech.framework.dto.carsourcecar.CarSourceCarAddDto;

import lombok.Getter;
import lombok.Setter;

/**
 * 添加车源入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourceAddDto {
	
	/**车辆类型*/
	@NotEmpty(message = "车辆类型不能为空")
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


