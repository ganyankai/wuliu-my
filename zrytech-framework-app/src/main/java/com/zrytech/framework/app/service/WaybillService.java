package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.entity.Waybill;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.app.dto.waybill.WaybillPageDto;
import com.zrytech.framework.app.dto.waybill.WaybillUpdateDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillDetailAddDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillDetailUpdateDto;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface WaybillService {
	
    /**
     * 运单分页
     * @author cat
     * 
     * @param dto	搜索条件
     * @param pageNum
     * @param pageSize
     * @return
     */
	PageData<Waybill> waybillPage(WaybillPageDto dto, Integer pageNum, Integer pageSize);
	
	/**
     * 管理员 - 运单详情
     * @author cat
     * 
     * @param dto
     * @return
     */
    ServerResponse adminDetails(CommonDto dto);
    
	 /**
     * 断言运单属于当前登录车主
     * @author cat
     *
     * @param waybillId    运单Id
     * @param carOwnerId 车主Id
     */
	Waybill assertWaybillBelongToCurrentUser(Integer waybillId, Integer carOwnerId);

    /**
     * 货主 - 运单详情
     * @author cat
     *
     * @param dto 运单Id
     * @return
     */
    ServerResponse cargoOwnerDetails(CommonDto dto);
    
    /**
     * 车主 - 运单详情
     * @author cat
     *
     * @param dto 运单Id
     * @return
     */
    ServerResponse carOwnerDetails(CommonDto dto);

    /**
     * 车主及车主子账号 - 删除运单项及运单项下的装卸地（物理删除）
     * @author cat
     *
     * @param dto      运单项Id
     * @param customer 当前登录人
     * @return
     */
    ServerResponse deleteWaybillDetail(CommonDto dto);
    
    /**
     * 车主及车主子账号 - 删除运单装卸地（物理删除）
     * @author cat
     *
     * @param dto      运单装卸地Id
     * @param customer 当前登录人
     * @return
     */
    ServerResponse deleteBillLocation(CommonDto dto);
    
    /**
     * 车主及车主子账号 - 新增运单项
     * @author cat
     *
     * @param dto
     * @return
     */
    ServerResponse addWaybillDetail(WaybillDetailAddDto dto);

    /**
     * 车主及车主子账号 - 修改运单项
     * @author cat
     *
     * @param dto
     * @return
     */
    ServerResponse updateWaybillDetail(WaybillDetailUpdateDto dto);
    
    /**
     * 车主 - 提交运单
     * <p>车主修改完运单信息之后，提交运单，将运单状态改为：待确认</p>
     * @author cat
     *
     * @param dto	运单Id
     * @return
     */
    ServerResponse submit(CommonDto dto);
    
    /**
     * 货主 - 确认运单
     * @author cat
     * 
     * @param dto	运单Id
     * @return
     */
    ServerResponse confirm(CommonDto dto);
    
    /**
     * 车主 - 修改运单基本信息
     * @author cat
     * 
     * @param dto
     * @return
     */
    ServerResponse update(WaybillUpdateDto dto);
    
    /**
     * 车主 - 取消运单
     * @author cat
     * 
     * @param dto	运单Id
     * @return
     */
    ServerResponse cancel(CommonDto dto);
    
    
    
    /**
	 * 运单统计
	 */
	@Deprecated
	ServerResponse coundIndent();
    
}
