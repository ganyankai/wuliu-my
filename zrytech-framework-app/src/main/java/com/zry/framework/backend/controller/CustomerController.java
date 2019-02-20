package com.zry.framework.backend.controller;


import com.zry.framework.dto.CargoCustomerDto;
import com.zry.framework.entity.CargoCustomer;
import com.zry.framework.service.CargoCustomerService;
import com.zry.framework.utils.PasswordUtils;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.MatcherUtil;
import com.zrytech.framework.base.util.PasswordUtil;
import com.zrytech.framework.base.util.TokenUtil;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Api(description = "客户相关api")
@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CargoCustomerService cargoCustomerService;

    /**
     * Desintion:客户注册
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户对象
     * @return:ServerResponse
     */
    @PostMapping("/register")
    @ApiOperation(value = "客户注册")
    public ServerResponse register(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return cargoCustomerService.register(requestParams.getParams());
    }

    /**
     * Desintion:客户登录
     *
     * @author:jiangxiaoxiang
     * @param:DefaultCustomer
     * @return:ServerResponse
     */
    @PostMapping("/login")
    @ApiOperation(value = "客户登录")
    public ServerResponse login(@RequestBody RequestParams<DefaultCustomer> requestParams) {
        DefaultCustomer defaultCustomer = requestParams.getParams();
        System.out.println(defaultCustomer.getLoginCounter() + ":" + defaultCustomer.getPwd());
        CargoCustomer currentCustomer = cargoCustomerService.findByCargoCustomerCount(defaultCustomer); //获取用户完全信息
        defaultCustomer.setPwd(PasswordUtils.encryptStringPassword(defaultCustomer.getPwd(),defaultCustomer.getLoginCounter()));
        checkLogin(currentCustomer, defaultCustomer);
        String tokenStr = TokenUtil.obtainToken(currentCustomer);//放入redis缓存
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", tokenStr);
        currentCustomer.setPwd(null);
        map.put("customer", currentCustomer);
        return ServerResponse.successWithData(map);
    }

    /**
     * @Desinition:忘记密码
     * @Author:jxx
     * @param:requestParams
     * @return:ServerResponse
     */
    @RequestMapping("/forget")
    public ServerResponse forget(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        if (requestParams.getParams() == null || MatcherUtil.check(requestParams.getParams().getTel())) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return cargoCustomerService.forget(requestParams.getParams());
    }

    /**
     * @param requestParams
     * @Author:jxx
     * @return
     * @Desinition:退出
     */
    @RequestMapping("/logout")
    public ServerResponse logout(@RequestBody RequestParams requestParams) {
        String token = requestParams.getToken();
        if (StringUtils.isNotEmpty(token)) {
            TokenUtil.deleteToken(token);
        }
        return ServerResponse.success();
    }

    /**
     * @Desinition:客户修改密码(登录成功后修改)
     * @param:CargoCustomerDto
     * @Author:jxx
     * @return
     */
    @PostMapping("/customer/savepassword")
    @ApiOperation(value = "修改密码")
    public ServerResponse savePassword(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        if(requestParams.getParams()==null||
                requestParams.getParams().getId()==null){
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return cargoCustomerService.savePassword(requestParams.getParams());
    }

    /**
     * @Desinition:客户修改手机号(登录成功后修改)
     * @param:CargoCustomerDto
     * @Author:jxx
     * @return
     */
    @PostMapping("/customer/updatePhone")
    @ApiOperation(value = "修改手机号")
    public ServerResponse updatePhone(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        if(requestParams.getParams()==null||
                requestParams.getParams().getId()==null){
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return cargoCustomerService.updatePhone(requestParams.getParams());
    }

    /**
     * @Desinition:用户名和密码登录验证
     * @param:CargoCustomer
     * @param:DefaultCustomer
     **/
    private void checkLogin(CargoCustomer dbCustomer, DefaultCustomer webUser) {
        if (!dbCustomer.getPwd().equalsIgnoreCase(webUser.getPwd())) {
            throw new BusinessException(new CommonResult(ResultEnum.INCORRECT_CREDENTIALS_ERROR));
        }
        if (dbCustomer.getIsActive() !=null&& dbCustomer.getIsActive()) {//isActive:true表示禁用;false表示启用
            throw new BusinessException(new CommonResult(ResultEnum.LOCKED_ACCOUNT_ERROR));
        }
    }

    public static class DefaultCustomer extends CargoCustomer implements Serializable {
        private static final long serialVersionUID = 5389110833420368253L;

        public DefaultCustomer() {
        }
    }

}
