package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.CargoCustomerDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
	
	/**
	 * 断言用户存在
	 * @author cat
	 * 
	 * @param userAccount
	 * @return
	 */
	public Customer assertCustomerExist(String userAccount);
	
	
    /**
     * 断言手机号未被注册
     * @author cat
     * 
     * @param tel	手机号
     */
    public void assertTelNotExist(String tel);
    
	/**
	 * 断言手机号存在
	 * @author cat
	 * 
	 * @param tel	手机号
	 * @return	手机号对应的账号
	 */
	public Customer assertTelExist(String tel);
	
	/**
	 * 断言用户不存在
	 * @author cat
	 * 
	 * @param tel	手机号
	 * @param userAccount	用户名
	 */
	public void assertCustomerNotExist(String tel, String userAccount);
	
	
    /**
     * @Desinition:子账号类列表展示
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    ServerResponse childAccountPage(CargoCustomerDto cargoCustomerDto, Page page);

    /**
     * @Desinition:子账号添加
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    ServerResponse addAccount(CargoCustomerDto cargoCustomerDto);

    /**
     * @Desinition:子账号详情
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    ServerResponse detail(CargoCustomerDto cargoCustomerDto);

    /**
     * @Desinition:子账号修改
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    ServerResponse updateAccount(CargoCustomerDto cargoCustomerDto);

    /**
     * @Desinition:子账号删除
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    ServerResponse deleteAccount(CargoCustomerDto cargoCustomerDto);
}
