package com.zrytech.framework.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.repository.EvaluateRepository;
import com.zrytech.framework.app.service.EvaluateService;

@Service
public class EvaluateServiceImpl implements EvaluateService {
	
	@Autowired private EvaluateRepository evaluateRepository;
	
	
	

}
