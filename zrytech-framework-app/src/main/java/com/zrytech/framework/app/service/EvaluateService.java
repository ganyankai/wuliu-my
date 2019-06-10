package com.zrytech.framework.app.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.evaluate.EvaluateAddDto;
import com.zrytech.framework.app.dto.evaluate.EvaluateSearchDto;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface EvaluateService {
	
	/**
	 * 评价等级平均值，保留一位小数
	 * @author cat
	 * 
	 * @param appraiserById	被评价人Id
	 * @return
	 */
	BigDecimal levelAVG(Integer appraiserById);
	
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
	 * 公开的被评价人的评价分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ServerResponse openPage(EvaluateSearchDto dto, Integer pageNum, Integer pageSize);
		
}
