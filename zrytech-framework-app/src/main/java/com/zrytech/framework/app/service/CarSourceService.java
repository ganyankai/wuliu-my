package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceDelDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceSaveDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourceAddDto;
import com.zrytech.framework.app.dto.carsource.CarSourceCheckUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourceNoCheckUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourcePageDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarDelDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarSaveDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarUpdateDto;
import com.zrytech.framework.app.entity.CarSource;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

@Service
public interface CarSourceService {
	
	/**
	 * 车源分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageData<CarSource> carSourcePage(CarSourcePageDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 管理员 - 车源详情
	 * @author cat
	 * 
	 * @param dto	车源Id
	 * @return
	 */
	public ServerResponse adminDetails(DetailsDto dto);
	
	/**
	 * 管理员 - 车源审批
	 * @author cat
	 * 
	 * @param dto
	 * @param user
	 * @return
	 */
	public ServerResponse adminApprove(ApproveDto dto, User user);

	
	/**
	 * 车主及车主子账号 - 新增车源
	 * @author cat
	 * 
	 * @param dto	新增车源入参
	 * @param customer	当前登录人（车主）
	 * @return
	 */
	public ServerResponse createCarSource(CarSourceAddDto dto, Customer customer);
	
	
	/**
	 * 车主及车主子账号 - 车源详情
	 * @author cat
	 * 
	 * @param dto	车源Id
	 * @param customer	车主及车主子账号
	 * @return
	 */
	public ServerResponse details(DetailsDto dto, Customer customer);
	
	
	/**
	 * 车主及车主子账号 - 修改车源基本信息需要审核的字段
	 * @author cat
	 * 
	 * @param dto	待修改字段
	 * @param customer	当前登录人（车主）
	 * @return
	 */
	public ServerResponse updateNeedApprove(CarSourceCheckUpdateDto dto, Customer customer);
	
	
	/**
	 * 车主及车主子账号 - 修改起止地（包含起止地的更新和添加）
	 * <p>当 {@link CarRecordPlaceUpdateDto} 的id为空时，表示在新增，id不为空时表示更新</P>
	 * @author cat
	 * 
	 * @param dto
	 * @param customer	当前登录人（车主）
	 * @return
	 */
	public ServerResponse addOrUpdateCarRecordPlace(CarRecordPlaceSaveDto dto, Customer customer);
	
	/**
	 * 车主及车主子账号 - 车源之新增车辆或更新车辆
	 * <p>当 {@link CarSourceCarUpdateDto} 的id为空时，表示在新增，id不为空时表示更新</P>
	 * @author cat
	 * 
	 * @param dto
	 * @param customer	当前登录人
	 * @return
	 */
	public ServerResponse saveCarSourceCar(CarSourceCarSaveDto dto, Customer customer);
	
	/**
	 * 删除车源起止地，最后一条记录无法删除，必须保留至少一条起止地数据
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	public ServerResponse delCarRecordPlace(CarRecordPlaceDelDto dto);
	
	/**
	 * 删除车源的车辆，最后一条记录无法删除，必须保留至少一条车源的车辆数据
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	public ServerResponse delCarSourceCar(CarSourceCarDelDto dto);
	
	/**
	 * 修改车源不需要审核的信息
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	public ServerResponse updateNoCheck(CarSourceNoCheckUpdateDto dto);
	
	
	/**
	 * 车源下架
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	public ServerResponse down(CommonDto dto);
	
	
}
