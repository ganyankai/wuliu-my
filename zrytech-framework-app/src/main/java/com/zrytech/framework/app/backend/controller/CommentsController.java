package com.zrytech.framework.app.backend.controller;

import com.zrytech.framework.app.dto.EvaluationDto;
import com.zrytech.framework.app.service.CommentsService;
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

@Api(description = "评论相关api")
@RestController
@RequestMapping("/comments")
@Deprecated
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    /**
     * Desintion:评论分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:EvaluationDto评论dto
     * @return:ServerResponse
     */
    @PostMapping("/page")
    @ApiOperation(value = "货源分页列表信息")
    public ServerResponse evaluationPage(@RequestBody RequestParams<EvaluationDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return commentsService.evaluationPage(requestParams.getParams(),requestParams.getPage());
    }

    /**
     * Desintion:评论详情
     *
     * @author:jiangxiaoxiang
     * @param:EvaluationDto评论dto
     * @return:ServerResponse
     */
    @PostMapping("/get")
    @ApiOperation(value = "评论详情")
    public ServerResponse get(@RequestBody RequestParams<EvaluationDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId()==null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return commentsService.get(requestParams.getParams());
    }

    /**
     * Desintion:评论删除
     *
     * @author:jiangxiaoxiang
     * @param:EvaluationDto评论dto
     * @return:ServerResponse
     */
    @PostMapping("/delete")
    @ApiOperation(value = "评论删除")
    public ServerResponse delete(@RequestBody RequestParams<EvaluationDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId()==null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return commentsService.delete(requestParams.getParams());
    }
}
