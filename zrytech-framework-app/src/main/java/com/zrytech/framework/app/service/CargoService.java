package com.zrytech.framework.app.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.dto.CargoDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.cargolocation.CargoLocationUpdateDto;
import com.zrytech.framework.app.dto.cargosource.CargoSourceAddDto;
import com.zrytech.framework.app.dto.cargosource.CargoSourceCheckUpdateDto;
import com.zrytech.framework.app.dto.cargosource.CargoSourceNoCheckUpdateDto;
import com.zrytech.framework.app.dto.cargosource.CargoSourceSearchDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.Cargo;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.Loading;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.entity.User;
import com.zrytech.framework.base.util.RequestUtil;

public interface CargoService {
    /**
     * Desintion:货源分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse cargoPage(CargoDto cargoDto, Page page);

    /**
     * Desintion:货源详情
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse get(CargoDto cargoDto);

    /**
     * Desintion:货源审核
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse auditSource(CargoDto cargoDto,User user);

    /**
     * Desintion:发布货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse pushResource(CargoDto cargoDto);

    /**
     * Desintion:修改货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse updateSource(CargoDto cargoDto);

    /**
     * Desintion:删除货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse deleteSource(CargoDto cargoDto);

    /**
     * Desintion:邀请报价(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse invitationOffer(CargoDto cargoDto);

    /**
     * Desintion:我的货源分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse mySourcePage(CargoDto cargoDto, Page page);

    /**
     * Desintion:取消发布货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    ServerResponse cancelResource(CargoDto cargoDto);
    
    /**
     * 断言货源可用
     * @author cat
     * 
     * @param cargoId	货源Id
     * @return
     */
    public Cargo assertCargoAvailable(Integer cargoId);
    
    
    
    /**
     * 货主 - 发布货源
     * @author cat
     * 
     * @param dto	货源基本信息和装卸地信息
     * @return
     */
    ServerResponse saveCargoSource(CargoSourceAddDto dto);
    
    
    /**
     * 货主更新货源的装卸地，传入的货源装卸地覆盖已存在的货源装卸地
     * @author cat
     * 
     * @param dto
     * @return
     */
    ServerResponse updateCargoLocations(CargoLocationUpdateDto dto);
    
    
	/**
	 * 货主取消货源，仅下架状态的货源可以取消
	 * 
	 * @param dto
	 *            货源Id
	 * @return
	 */
	ServerResponse cancel(CommonDto dto);
	
	
	/**
	 * 货源搜索分页
	 * @author cat
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param dto	搜索条件
	 * @return
	 */
	ServerResponse search(Integer pageNum, Integer pageSize, CargoSourceSearchDto dto);
    
	/**
	 * 货主查看货源详情
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse details(CommonDto dto);
	
	
	/**
	 * 货主提交审核，仅下架状态的货源可以提交审核
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse submitChcek(CommonDto dto);
	
	/**
	 * 货主下架货源，仅待审核、发布中的货源可以下架
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse down(CommonDto dto);
	
	/**
	 * 货主修改货源不需要审核的内容，仅下架、发布中的货源可以修改
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse updateNoCheck(CargoSourceNoCheckUpdateDto dto);
	
	/**
	 * 货主修改货源需要审核的内容，仅下架的货源可以修改
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse updateCheck(CargoSourceCheckUpdateDto dto);
	
	
	/**
	 * 公开货源的详情，仅展示发布中，已完成货源的详情
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse openDetails(CommonDto dto);
	
	
	/**
	 * 管理员审核货源
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	ServerResponse adminCheckCargoSource(ApproveDto dto);
	
}
