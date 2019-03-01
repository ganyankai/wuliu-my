package com.zrytech.framework.app.backend.controller;

import com.zrytech.framework.app.dto.FocusDto;
import com.zrytech.framework.app.service.FocusOnService;
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

@Api(description = "关注相关api")
@RestController
@RequestMapping("/focusOn")
public class FocusOnController {

    @Autowired
    private FocusOnService focusOnService;

    /**
     * Desintion:关注分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    @PostMapping("/page")
    @ApiOperation(value = "关注分页列表信息")
    public ServerResponse focusPage(@RequestBody RequestParams<FocusDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return focusOnService.focusPage(requestParams.getParams(),requestParams.getPage());
    }

    /**
     * Desintion:关注详情
     *
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    @PostMapping("/get")
    @ApiOperation(value = "关注详情")
    public ServerResponse get(@RequestBody RequestParams<FocusDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId()==null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return focusOnService.get(requestParams.getParams());
    }

    /**
     * Desintion:删除关注
     *
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除关注")
    public ServerResponse delete(@RequestBody RequestParams<FocusDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId()==null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return focusOnService.delete(requestParams.getParams());
    }

    /**
     * Desintion:添加关注
     *
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加关注")
    public ServerResponse add(@RequestBody RequestParams<FocusDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return focusOnService.add(requestParams.getParams());
    }

    /**
     * Desintion:我的关注
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    @PostMapping("/selectMyFocus")
    @ApiOperation(value = "我的关注")
    public ServerResponse selectMyFocus(@RequestBody RequestParams<FocusDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return focusOnService.selectMyFocus(requestParams.getParams());
    }
}
