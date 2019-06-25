package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.app.constants.CustomerConstants;
import com.zrytech.framework.app.dto.MessageDto;
import com.zrytech.framework.app.dto.MessageReadDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.service.MessageService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.SysCustomer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(description = "消息相关api")
@RestController
@RequestMapping("/message")
public class MessageApiController {

    @Autowired
    private MessageService messageService;

    /**
     * 一键已读
     * @return
     */
    @Valid
    @PostMapping("/markRead")
    public ServerResponse markRead(@RequestBody  @Valid  RequestParams<MessageReadDto> requestParams, BindingResult result){
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
        Integer reveicerId = 0;
        if(customer.getCustomerType().equalsIgnoreCase(CustomerConstants.TYPE_CAR_OWNER)){
            Integer carId = customer.getCarOwner().getId();
            //车主
            reveicerId =  carId;
        }else if(customer.getCustomerType().equalsIgnoreCase(CustomerConstants.TYPE_CARGO_OWNER)){
            //货主
            Integer cargoId = customer.getCargoOwner().getId();
            reveicerId =  cargoId;
        }else if(customer.getCustomerType().equalsIgnoreCase(CustomerConstants.TYPE_DRIVER)){
            //司机
            reveicerId = customer.getDirver().getId();
        }

        List<Integer> idsList = requestParams.getParams().getIdsList();
        return messageService.markRead(reveicerId,idsList);
    }

    /**
     * 获取当前用户不同分类下的未读消息
     * @return
     */
    @PostMapping("/accessMessageCount")
    public ServerResponse accessMessageCount(){
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
        Integer reveicerId = 0;
        if(customer.getCustomerType().equalsIgnoreCase(CustomerConstants.TYPE_CAR_OWNER)){
            Integer carId = customer.getCarOwner().getId();
            //车主
            reveicerId =  carId;
        }else if(customer.getCustomerType().equalsIgnoreCase(CustomerConstants.TYPE_CARGO_OWNER)){
            //货主
            Integer cargoId = customer.getCargoOwner().getId();
            reveicerId =  cargoId;
        }else if(customer.getCustomerType().equalsIgnoreCase(CustomerConstants.TYPE_DRIVER)){
            //司机
            reveicerId = customer.getDirver().getId();
        }

        return messageService.accessMessageCount(reveicerId);
    }

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
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
        Integer reveicerId = 0;
        if(customer.getCustomerType().equalsIgnoreCase(CustomerConstants.TYPE_CAR_OWNER)){
            Integer carId = customer.getCarOwner().getId();
            //车主
            reveicerId =  carId;
        }else if(customer.getCustomerType().equalsIgnoreCase(CustomerConstants.TYPE_CARGO_OWNER)){
            //货主
            Integer cargoId = customer.getCargoOwner().getId();
            reveicerId =  cargoId;
        }else if(customer.getCustomerType().equalsIgnoreCase(CustomerConstants.TYPE_DRIVER)){
            //司机
            reveicerId = customer.getDirver().getId();
        }

        messageDto.setReveicerId(reveicerId);
        return messageService.selectTypePage(requestParams.getParams(), requestParams.getPage());
    }
    /**
     *@Desinition:消息查看
     *@Author:Jxx
     *@param:MessageDto
     *@return:
     * */
}
