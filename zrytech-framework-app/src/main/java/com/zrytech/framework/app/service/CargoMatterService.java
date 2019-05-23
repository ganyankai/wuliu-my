package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CargoMatterPageDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterAddDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterUpdateDto;
import com.zrytech.framework.app.entity.CargoMatter;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.entity.User;

@Service
public interface CargoMatterService {
	
	/**
	 * 报价单分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageData<CargoMatter> cargoMatterPage(CargoMatterPageDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 管理员 - 报价单详情
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	public ServerResponse adminDetails(DetailsDto dto);
	
	/**
	 * 管理员 -  报价单审批
	 * @author cat
	 * 
	 * @param dto
	 * @param user
	 * @return
	 */
	public ServerResponse adminApprove(ApproveDto dto, User user);
	
	
	
	

	/**
	 * 车主 - 修改报价单
	 * @author cat
	 * 
	 * @param dto	报价单Id和报价单的价格
	 * @return
	 */
	ServerResponse update(CargoMatterUpdateDto dto);
	
	/**
	 * 车主 - 删除报价单
	 * @author cat
	 * 
	 * @param dto	报价单Id
	 * @return
	 */
	ServerResponse delete(CommonDto dto);
	
	/**
	 * 车主 - 报价，生成报价单
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse add(CargoMatterAddDto dto);
	
	/**
	 * 车主 - 我的报价单分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ServerResponse carOwnerCargoMatterPage(CargoMatterPageDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 车主 - 我的报价单详情
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse carOwnerCargoMatterDetails(CommonDto dto);
	
	/**
	 * 车主 - 抢标
	 * @author cat
	 * 
	 * @param dto	货源Id
	 * @return
	 */
	ServerResponse bid(CommonDto dto);
	
	
	
	
	/**
	 * 货主 - 查看货源的报价单列表
	 * @author cat
	 * 
	 * @param dto	货源Id必填
	 * @return
	 */
	ServerResponse cargoOwnerGetCargoMatterByCargoId(CargoMatterPageDto dto);
	
	/**
	 * 货主 - 中标
	 * @author cat
	 * 
	 * @param dto	报价单Id
	 * @return
	 */
	ServerResponse tender(CommonDto dto);
	
}
