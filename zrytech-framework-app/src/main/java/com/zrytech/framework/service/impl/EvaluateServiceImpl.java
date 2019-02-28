package com.zrytech.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.repository.EvaluateRepository;
import com.zrytech.framework.service.EvaluateService;

@Service
public class EvaluateServiceImpl implements EvaluateService {
	
	@Autowired private EvaluateRepository evaluateRepository;
	
	
	

}
