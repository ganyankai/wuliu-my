package com.zry.framework.backend.controller;

import com.zry.framework.dto.FocusLineDto;
import com.zry.framework.service.FocusLineService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "线路相关api")
@RestController
@RequestMapping("/line")
public class FocusLineController {


    @Autowired
    private FocusLineService focusLineService;

    /**
     * Desintion:路线分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:FocusLineDto路线dto
     * @return:ServerResponse
     */
    @PostMapping("/page")
    @ApiOperation(value = "路线分页列表信息")
    public ServerResponse linePage(@RequestBody RequestParams<FocusLineDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return focusLineService.linePage(requestParams.getParams(),requestParams.getPage());
    }

    /**
     * Desintion:路线详情
     *
     * @author:jiangxiaoxiang
     * @param:FocusLineDto路线dto
     * @return:ServerResponse
     */
    @PostMapping("/get")
    @ApiOperation(value = "路线详情")
    public ServerResponse get(@RequestBody RequestParams<FocusLineDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId()==null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return focusLineService.get(requestParams.getParams());
    }

    /**
     * Desintion:删除路线
     *
     * @author:jiangxiaoxiang
     * @param:FocusLineDto路线dto
     * @return:ServerResponse
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除路线")
    public ServerResponse delete(@RequestBody RequestParams<FocusLineDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId()==null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return focusLineService.delete(requestParams.getParams());
    }

    /**
     * Desintion:添加路线
     *
     * @author:jiangxiaoxiang
     * @param:FocusLineDto路线dto
     * @return:ServerResponse
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加路线")
    public ServerResponse add(@RequestBody RequestParams<FocusLineDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return focusLineService.add(requestParams.getParams());
    }
}
