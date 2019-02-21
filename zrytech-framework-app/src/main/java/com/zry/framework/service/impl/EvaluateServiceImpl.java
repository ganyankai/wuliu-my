package com.zry.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zry.framework.repository.EvaluateRepository;
import com.zry.framework.service.EvaluateService;

@Service
public class EvaluateServiceImpl implements EvaluateService {
	
	@Autowired private EvaluateRepository evaluateRepository;
	
	
	

}
