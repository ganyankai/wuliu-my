package com.zrytech.framework.app.dto.carsource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	/**运输量*/
    @NotNull(message = "运输量不能为空")
    private Integer qty;
    
    /**运输量单位*/
    @NotBlank(message = "运输量单位不能为空")
	private String unit;
	
    /**车源的路线*/
    @Valid
    @NotNull(message = "车源起止地不能为空")
    @Size(min = 1, max = 1, message = "当前只能有一个车源起止地")
    List<CarRecordPlaceAddDto> carRecordPlaces;
    
    /**车源的车辆司机压货人*/
    @Valid
    List<CarSourceCarAddDto> carSourceCars;

}


