package com.zrytech.framework.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zrytech.framework.app.entity.Evaluate;

public interface EvaluateMapper {

	List<Evaluate> search(@Param("evaluate") Evaluate evaluate);

}