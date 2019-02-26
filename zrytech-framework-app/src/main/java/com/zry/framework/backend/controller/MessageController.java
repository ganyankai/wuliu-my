package com.zry.framework.backend.controller;


import com.zry.framework.dto.MessageDto;
import com.zry.framework.service.MessageService;
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

@Api(description = "消息相关api")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * Desintion:消息分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    @PostMapping("/page")
    @ApiOperation(value = "消息分页列表信息")
    public ServerResponse messagePage(@RequestBody RequestParams<MessageDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return messageService.messagePage(requestParams.getParams(), requestParams.getPage());
    }


    /**
     * Desintion:消息详情
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    @PostMapping("/get")
    @ApiOperation(value = "消息详情")
    public ServerResponse get(@RequestBody RequestParams<MessageDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return messageService.get(requestParams.getParams());
    }

    /**
     * Desintion:消息添加
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    @PostMapping("/addMessage")
    @ApiOperation(value = "消息添加")
    public ServerResponse addMessage(@RequestBody RequestParams<MessageDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return messageService.addMessage(requestParams.getParams());
    }

    /**
     * Desintion:消息删除
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    @PostMapping("/delete")
    @ApiOperation(value = "消息删除")
    public ServerResponse delete(@RequestBody RequestParams<MessageDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return messageService.delete(requestParams.getParams());
    }


    /**
     * Desintion:消息查看
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    @PostMapping("/selectMsgList")
    @ApiOperation(value = "消息查看")
    public ServerResponse selectMsgList(@RequestBody RequestParams<MessageDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return messageService.selectMsgList(requestParams.getParams());
    }

}
