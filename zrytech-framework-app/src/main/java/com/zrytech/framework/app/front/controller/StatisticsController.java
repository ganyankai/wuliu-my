package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.app.dto.StatisticsCommonDto;
import com.zrytech.framework.app.service.StatisticsService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;
    /**
     * Desintion:统计信息
     *
     * @author:dante
     * @param:统计dto
     * @return:ServerResponse
     */
    @Valid
    @PostMapping("/count")
    public ServerResponse count(@RequestBody RequestParams<StatisticsCommonDto> requestParams, BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return statisticsService.count(requestParams.getParams().getId());
    }


}
