package com.zrytech.framework.dto.carrecordplace;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * 更新路线或新增路线入参
 * <p>id为空表示新增，id不为空表示更新</p>
 * @author cat
 *
 */
@Setter
@Getter
public class CarRecordPlaceUpdateDto {
	
	/**路线Id*/
	private Integer id;
	
	/**出发省*/
	@NotEmpty(message = "出发省不能为空")
    private String startProvince;

	/**出发市*/
	@NotEmpty(message = "出发市不能为空")
	private String startCity;

	/**出发县*/
	@NotEmpty(message = "出发县不能为空")
	private String startCountry;

	/**到达省*/
	@NotEmpty(message = "到达省不能为空")
	private String endProvince;

	/**到达市*/
	@NotEmpty(message = "到达市不能为空")
	private String endCity;

	/**到达县*/
	@NotEmpty(message = "到达县不能为空")
	private String endCountry;
	
}
