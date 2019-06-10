package com.zrytech.framework.app.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zrytech.framework.app.entity.Evaluate;

public interface EvaluateMapper {

	List<Evaluate> search(@Param("evaluate") Evaluate evaluate);
	
	@Select("SELECT AVG(`evaluate_level`) FROM `evaluate` WHERE `appraiser_by_id` = #{appraiserById}")
	BigDecimal levelAVG(@Param("appraiserById") Integer appraiserById);

}