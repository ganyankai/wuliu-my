package com.zry.framework.front.controller;

import com.zry.framework.dto.MessageDto;
import com.zry.framework.service.MessageService;
import com.zrytech.framework.base.annotation.CurrentCustomer;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.SysCustomer;
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
public class MessageApiController {

    @Autowired
    private MessageService messageService;

    /**
     * Desintion:消息分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:MessageDto消息dto
     * @return:ServerResponse
     */
    @PostMapping("/selectTypePage")
    @ApiOperation(value = "消息分页列表信息")
    public ServerResponse selectTypePage(@RequestBody RequestParams<MessageDto> requestParams) {
        MessageDto messageDto=requestParams.getParams();
        if (messageDto== null) {
            messageDto=new MessageDto();
        }
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        messageDto.setReveicerId(RequestUtil.getCurrentUser(SysCustomer.class).getId());
        return messageService.selectTypePage(requestParams.getParams(), requestParams.getPage());
    }
    /**
     *@Desinition:消息查看
     *@Author:Jxx
     *@param:MessageDto
     *@return:
     * */
}
