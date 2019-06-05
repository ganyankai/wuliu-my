package com.zrytech.framework.app.backend.controller;

import com.zrytech.framework.app.dto.hotplace.HotPlaceAddDto;
import com.zrytech.framework.app.dto.hotplace.HotPlaceCommonDto;
import com.zrytech.framework.app.dto.hotplace.HotPlacePageDto;
import com.zrytech.framework.app.dto.hotplace.HotPlaceUpdateDto;
import com.zrytech.framework.app.service.HotPlaceService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(description = "热门货源地")
@RestController
@RequestMapping("/hotPlace")
public class HotPlaceController {

    @Autowired
    private HotPlaceService hotPlaceService;
    /**
     * Desintion:热门货源地分页列表信息
     *
     * @author:dante
     * @param:HotPlaceDto热门货源地dto
     * @return:ServerResponse
     */
    @Valid
    @PostMapping("/page")
    public ServerResponse hotPlacePage(@RequestBody RequestParams<HotPlacePageDto> requestParams, BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return hotPlaceService.hotPlacePage(requestParams.getParams(),requestParams.getPage());
    }

    /**
     * Desintion:热门货源地添加
     *
     * @author:dante
     * @param:HotPlaceDto热门货源地dto
     * @return:ServerResponse
     */
    @Valid
    @PostMapping("/add")
    @ApiOperation(value = "热门货源地添加")
    public ServerResponse add(@RequestBody @Valid RequestParams<HotPlaceAddDto> requestParams, BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return hotPlaceService.add(requestParams.getParams());
    }

    /**
     * Desintion:热门货源地详情
     *
     * @author:dante
     * @param:HotPlaceDto热门货源地dto
     * @return:ServerResponse
     */
    @Valid
    @PostMapping("/get")
    public ServerResponse get(@RequestBody @Valid RequestParams<HotPlaceCommonDto> requestParams,BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }

        return hotPlaceService.get(requestParams.getParams());
    }

    /**
     * Desintion:热门货源地修改
     *
     * @author:dante
     * @param:HotPlaceDto热门货源地dto
     * @return:ServerResponse
     */
    @Valid
    @PostMapping("/update")
    @ApiOperation(value = "热门货源地修改")
    public ServerResponse update(@RequestBody @Valid RequestParams<HotPlaceUpdateDto> requestParams, BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return hotPlaceService.update(requestParams.getParams());
    }

    /**
     * Desintion:热门货源地删除
     *
     * @author:dante
     * @param:HotPlaceDto热门货源地dto
     * @return:ServerResponse
     */
    @Valid
    @PostMapping("/delete")
    @ApiOperation(value = "热门货源地删除")
    public ServerResponse delete(@RequestBody @Valid RequestParams<HotPlaceCommonDto> requestParams, BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return hotPlaceService.delete(requestParams.getParams());
    }
}
