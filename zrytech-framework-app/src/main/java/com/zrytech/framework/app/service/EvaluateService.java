package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.evaluate.EvaluateAddDto;
import com.zrytech.framework.app.dto.evaluate.EvaluateSearchDto;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface EvaluateService {
	
	/**
	 * 车主 - 车主评价货主
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse carEvaluateCargo(EvaluateAddDto dto);
	
	/**
	 * 货主 - 货主评价车主
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse cargoEvaluateCar(EvaluateAddDto dto);
	
	/**
	 * 车主 - 来自货主的评价分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ServerResponse fromCargo(EvaluateSearchDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 车主 - 对货主的评价分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ServerResponse toCargo(EvaluateSearchDto dto, Integer pageNum, Integer pageSize);

	/**
	 * 货主 - 来自车主的评价分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ServerResponse fromCar(EvaluateSearchDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 货主 - 对车主的评价分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ServerResponse toCar(EvaluateSearchDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 公开 - 车主的评价
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ServerResponse openCar(EvaluateSearchDto dto, Integer pageNum, Integer pageSize);
		
	/**
	 * 公开 - 货主的评价
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ServerResponse openCargo(EvaluateSearchDto dto, Integer pageNum, Integer pageSize);
		
	
}
