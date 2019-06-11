package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CarCargoOwnerConstants;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.constants.CargoMatterConstants;
import com.zrytech.framework.app.dao.CargoCustomerDao;
import com.zrytech.framework.app.dao.CargoDao;
import com.zrytech.framework.app.dao.LoadingDao;
import com.zrytech.framework.app.dao.ShipperDao;
import com.zrytech.framework.app.dto.CargoDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.cargolocation.CargoLocationAddDto;
import com.zrytech.framework.app.dto.cargolocation.CargoLocationUpdateDto;
import com.zrytech.framework.app.dto.cargosource.*;
import com.zrytech.framework.app.dto.hotplace.HotPlacePageDto;
import com.zrytech.framework.app.entity.*;
import com.zrytech.framework.app.enums.LogisticsResult;
import com.zrytech.framework.app.enums.LogisticsResultEnum;
import com.zrytech.framework.app.mapper.CarCargoOwnerMapper;
import com.zrytech.framework.app.mapper.CargoMapper;
import com.zrytech.framework.app.mapper.LoadingMapper;
import com.zrytech.framework.app.repository.*;
import com.zrytech.framework.app.service.ApproveLogService;
import com.zrytech.framework.app.service.CarCargoOwnerService;
import com.zrytech.framework.app.service.CargoService;
import com.zrytech.framework.app.service.EvaluateService;
import com.zrytech.framework.app.service.MyFocusPersonService;
import com.zrytech.framework.app.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.entity.User;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.BeanUtil;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.SysCustomer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private LoadingDao loadingDao;

    @Autowired
    private CargoCustomerDao cargoCustomerDao;

    @Autowired
    private ShipperDao shipperDao;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private CargoLocationRepository cargoLocationRepository;
    
    @Autowired
    private LoadingMapper loadingMapper;
    
    @Autowired
    private CargoMapper cargoMapper;

	@Autowired
	private LogisticsCustomerRepository logisticsCustomerRepository;

	@Autowired
	private CarCargoOwnerMapper carCargoOwnerMapper;

	@Value("${cargo.bidWaitTime}")
	private String bidWaitTime;

	@Override
	public ServerResponse adminPage(Integer pageNum, Integer pageSize, CargoSourceSearchDto dto) {
		com.github.pagehelper.Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<Cargo> list = cargoMapper.cargoSearch(dto);
		for (Cargo cargo : list) {
			CarCargoOwnner cargoOwner = carCargoOwnnerRepository.findOne(cargo.getCreateBy());
			cargo.setCargoOwnerName(cargoOwner.getName());
			cargo.setCargoOwnerTel(cargoOwner.getTel());
			int countByCargoId = cargoMatterRepository.countByCargoId(cargo.getId());
			cargo.setCargoMatterCount(countByCargoId);
			cargo.setLogo(cargoOwner.getHeadImg());
		}
		PageData<Cargo> pageData = new PageData<>(page.getPageSize(), page.getPageNum(), page.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
    
    @Override
	public ServerResponse adminDetails(CommonDto dto) {
		Cargo cargo = this.assertCargoAvailable(dto.getId());
		Integer createBy = cargo.getCreateBy();
		CarCargoOwnner cargoOwner = carCargoOwnnerRepository.findOne(createBy);
		cargo.setCargoOwnerName(cargoOwner.getName());
		cargo.setCargoOwnerTel(cargoOwner.getTel());
		int countByCargoId = cargoMatterRepository.countByCargoId(cargo.getId());
		cargo.setCargoMatterCount(countByCargoId);
		if (cargo.getStatus().equalsIgnoreCase(CargoConstant.CARGO_SOURCE_STATUS_COMPLETED)) {
			List<CargoMatter> cargoMatters = cargoMatterRepository.findByCargoIdAndStatus(cargo.getId(),
					CargoMatterConstants.CARGO_MATTER_STATUS_TENDER);
			CargoMatter cargoMatter = cargoMatters.get(0);
			Integer carOwnnerId = cargoMatter.getCarOwnnerId();
			CarCargoOwnner carOwner = carCargoOwnnerRepository.findOne(carOwnnerId);
			if (carOwner.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_PERSON)) {
				cargoMatter.setCarOwnerName(carOwner.getLegalerName());
			} else {
				cargoMatter.setCarOwnerName(carOwner.getName());
			}
			cargo.setCargoMatter(cargoMatter);
		}
		List<CargoMatter> cargoMatters = cargoMatterRepository.findByCargoId(cargo.getId());
		cargo.setCargoMatters(cargoMatters);
		
		List<Loading> list = cargoLocationRepository.findByCargoId(cargo.getId());
		cargo.setCargoLocations(list);
		return ServerResponse.successWithData(cargo);
	}
    
    
    @Autowired
    private ApproveLogRepository approveLogRepository;
    
    @Autowired
	private ApproveLogService approveLogService;
    
    
	@Transactional
	@Override
	public ServerResponse adminCheckCargoSource(ApproveDto dto) {
		User user = RequestUtil.getCurrentUser(User.class);
		Cargo cargo = this.assertCargoAvailable(dto.getBusinessId());
		if (!CargoConstant.CARGO_SOURCE_STATUS_WAIT_CHECK.equalsIgnoreCase(cargo.getStatus())) {
			throw new BusinessException(112, "审批失败：货源状态不是待审批");
		}

		if (ApproveConstants.RESULT_AGREE.equalsIgnoreCase(dto.getResult())) {
			cargoMapper.updateStatusById(cargo.getId(), CargoConstant.CARGO_SOURCE_STATUS_RELEASE);
			cargo.setStatus(CargoConstant.CARGO_SOURCE_STATUS_RELEASE);
			//消息推送逻辑
			this.pushGoodSource(cargo);


		} else {
			cargoMapper.updateStatusById(cargo.getId(), CargoConstant.CARGO_SOURCE_STATUS_DOWN);
			// TODO 短信通知
		}
		approveLogService.addApproveLog(dto, user.getId(), ApproveLogConstants.APPROVE_TYPE_CARGO_SOURCE);


		return ServerResponse.successWithData("审批成功");
	}
    
    
    

    public void pushGoodSource(Cargo cargoGoods) {
        if (cargoGoods != null && CargoConstant.TENDER_MARK.equalsIgnoreCase(cargoGoods.getTenderWay())) {
            //TODO:招标方式; 目前招标不推送
//            List<Integer> list = cargoCustomerDao.selectCarList(cargoGoods, CargoConstant.CUSTOMER_CAR_OWNER);
//            cargoDao.batch(list, cargoGoods.getId(), new Date());//批量添加推送记录
        } else {//抢标方式
//            List<Integer> list = cargoCustomerDao.selectCarList(cargoGoods, CargoConstant.CUSTOMER_CAR_OWNER);
			//TODO
			//随机挑选5名车主推送消息
			List<Integer> list = carCargoOwnerMapper.randSelectCarOwner();
            cargoDao.batch(list, cargoGoods.getId(), new Date());//批量添加推送记录
        }
    }
    
    
	private Cargo assertCargoBelongToCurrentCargoOwner(Integer cargoSourceId, Integer cargoOwnerId) {
		Cargo cargo = cargoRepository.findByIdAndCreateBy(cargoSourceId, cargoOwnerId);
		if (cargo == null) {
			throw new BusinessException(112, "货源不存在");
		}
		return cargo;
	}

	@Transactional
	public ServerResponse updateCargoLocations(CargoLocationUpdateDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer cargoOwnerId = cargoOwner.getId();
		Integer cargoSourceId = dto.getCargoSourceId();

		Cargo cargo = assertCargoBelongToCurrentCargoOwner(cargoSourceId, cargoOwnerId);
		if(!CargoConstant.CARGO_SOURCE_STATUS_DOWN.equalsIgnoreCase(cargo.getStatus())) {
			throw new BusinessException(112, "仅下架状态的货源可以修改装卸地");
		}
		
		// 货源装卸地
		List<CargoLocationAddDto> cargoLocationList = dto.getCargoLocationList();

		cargo = this.cargoLocationCheck(cargo, cargoLocationList);
		// cargoLocationList = this.cargoLocationSort(cargoLocationList);

		CargoLocationAddDto firstCargoLocation = cargoLocationList.get(0);
		CargoLocationAddDto lastCargoLocation = cargoLocationList.get(cargoLocationList.size() - 1);

		cargo = this.setCargoLine(cargo, firstCargoLocation, lastCargoLocation);
		cargoRepository.save(cargo);
		Integer cargoId = cargo.getId();

		loadingMapper.deleteByCargoId(cargoId);
		
		List<Loading> list = this.setCargoLocations(cargoLocationList, cargoId);
		cargoLocationRepository.save(list);

		return ServerResponse.success();
	}
    
	@Transactional
	public ServerResponse saveCargoSource(CargoSourceAddDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer cargoOwnerId = cargoOwner.getId();
		
		// 车源装卸地
		List<CargoLocationAddDto> cargoLocationList = dto.getCargoLocationList();
		
		Cargo cargo = new Cargo();
		cargo = this.cargoLocationCheck(cargo, cargoLocationList);
		
		// cargoLocationList = this.cargoLocationSort(cargoLocationList);

		CargoLocationAddDto firstCargoLocation = cargoLocationList.get(0);
		CargoLocationAddDto lastCargoLocation = cargoLocationList.get(cargoLocationList.size() - 1);

		BeanUtils.copyProperties(dto, cargo);
		if (StringUtils.isNotBlank(dto.getStatus())) {
			cargo.setStatus(dto.getStatus());
		} else {
			cargo.setStatus(CargoConstant.CARGO_SOURCE_STATUS_WAIT_CHECK);
		}
		cargo.setCreateDate(new Date());
		cargo.setCreateBy(cargoOwnerId);
		cargo = this.setCargoLine(cargo, firstCargoLocation, lastCargoLocation);
		cargo = cargoRepository.save(cargo);
		Integer cargoId = cargo.getId();

		List<Loading> list = this.setCargoLocations(cargoLocationList, cargoId);
		cargoLocationRepository.save(list);

		return ServerResponse.success();
	}
    
    
    /**
     * 设置将要保存的货源装卸地列表
     * @author cat
     * 
     * @param cargoLocations	货源装卸地列表
     * @param cargoId	货源Id
     * @return
     */
	private List<Loading> setCargoLocations(List<CargoLocationAddDto> cargoLocations, Integer cargoId) {
		List<Loading> list = new ArrayList<>();
		for (CargoLocationAddDto temp : cargoLocations) {
			Loading loading = new Loading();
			BeanUtils.copyProperties(temp, loading);
			loading.setCargoId(cargoId);
			list.add(loading);
		}
		return list;
	}
    
	
    /**
     * 设置货源的路线和起止时间
     * @author cat
     * 
     * @param cargo	货源
     * @param firstCargoLocation	最开始的装货地
     * @param lastCargoLocation	最后的卸货地
     * @return
     */
	private Cargo setCargoLine(Cargo cargo, CargoLocationAddDto firstCargoLocation,
			CargoLocationAddDto lastCargoLocation) {
		cargo.setStartProvince(firstCargoLocation.getProvince());
		cargo.setStartCity(firstCargoLocation.getCity());
		cargo.setStartCountry(firstCargoLocation.getCounty());
		cargo.setEndProvince(lastCargoLocation.getProvince());
		cargo.setEndCity(lastCargoLocation.getCity());
		cargo.setEndCountry(lastCargoLocation.getCounty());
		cargo.setPickupDate(firstCargoLocation.getLoadDate());
		cargo.setArrivalDate(lastCargoLocation.getLoadDate());
		return cargo;
	}
    
    /**
     * 将货源装卸地按照时间先后顺序排序，同时会验证第一个必须是装货地，最后一个必须是卸货地
     * @author cat
     * 
     * @param cargoLocationList	车源装卸地列表
     * @return 排序后的车源装卸地
     */
	private List<CargoLocationAddDto> cargoLocationSort(List<CargoLocationAddDto> cargoLocationList) {
		TreeMap<Long, CargoLocationAddDto> treeMap = new TreeMap<>();
		for (CargoLocationAddDto cargoLocationAddDto : cargoLocationList) {
			long time = cargoLocationAddDto.getLoadDate().getTime();
			treeMap.put(time, cargoLocationAddDto);
		}

		if(treeMap.size() - cargoLocationList.size() != 0) {
			throw new BusinessException(112, "不允许有装卸时间一样的装卸地");
		}
		
		List<CargoLocationAddDto> cargoLocations = new ArrayList<>();
		for (Entry<Long, CargoLocationAddDto> entry : treeMap.entrySet()) {
			cargoLocations.add(entry.getValue());
		}

		CargoLocationAddDto firstCargoLocation = cargoLocations.get(0);
		CargoLocationAddDto lastCargoLocation = cargoLocations.get(cargoLocations.size() - 1);
		if (CargoConstant.UNLOADING_TYPE.equalsIgnoreCase(firstCargoLocation.getType())) {
			throw new BusinessException(112, "最开始的一个地址不能是卸货地");
		}
		if (CargoConstant.LOADING_TYPE.equalsIgnoreCase(lastCargoLocation.getType())) {
			throw new BusinessException(112, "最后的一个地址不能是装货地");
		}

		return cargoLocations;
	}
	
	private Cargo cargoLocationCheck(Cargo cargo, List<CargoLocationAddDto> cargoLocationList) {
		int load = 0; // 装货地的数量
		int unload = 0; // 卸货地的数量
		for (CargoLocationAddDto cargoLocationAddDto : cargoLocationList) {
			if (CargoConstant.LOADING_TYPE.equalsIgnoreCase(cargoLocationAddDto.getType())) {
				load++;
			} else {
				unload++;
			}
		}

		if (load == 0) {
			throw new BusinessException(112, "至少有一个装货地");
		} else if (load == 1) {
			cargo.setMulShipment(false);
		} else {
			cargo.setMulShipment(true);
		}

		if (unload == 0) {
			throw new BusinessException(112, "至少有一个卸货地");
		} else if (unload == 1) {
			cargo.setMulUnload(false);
		} else {
			cargo.setMulUnload(true);
		}
		
		return cargo;
	}
	
    /**
     * 货源装卸地验证
     * @author cat
     * 
     * @param mulShipment	是否多点装货
     * @param mulUnload	是否多点卸货
     * @param cargoLocationList	货源装卸地列表
     * @return	货源装货地总装货数量
     */
	/*private Cargo cargoLocationCheck(Cargo cargo, List<CargoLocationAddDto> cargoLocationList) {
		int load = 0; // 装货地的数量
		int unload = 0; // 卸货地的数量
		Integer loadQty = 0; // 所有装货地运输数量之和
		Integer unloadQty = 0; // 所有卸货地运输数量之和
		for (CargoLocationAddDto cargoLocationAddDto : cargoLocationList) {
			Integer qty = cargoLocationAddDto.getQty();
			if (CargoConstant.LOADING_TYPE.equalsIgnoreCase(cargoLocationAddDto.getType())) {
				load++;
				loadQty = loadQty + qty;
			} else {
				unload++;
				unloadQty = unloadQty + qty;
			}
			Date loadDate = cargoLocationAddDto.getLoadDate();
			Date endDate = cargoLocationAddDto.getEndDate();
			if (endDate.before(loadDate)) {
				throw new BusinessException(112, "装卸时间不能大于截止时间");
			}
		}

		if (!loadQty.equals(unloadQty)) {
			throw new BusinessException(112, "装货总数量需要与卸货总数量一致");
		}

		if (load == 0) {
			throw new BusinessException(112, "至少有一个装货地");
		} else if (load == 1) {
			cargo.setMulShipment(false);
		} else {
			cargo.setMulShipment(true);
		}

		if (unload == 0) {
			throw new BusinessException(112, "至少有一个卸货地");
		} else if (unload == 1) {
			cargo.setMulUnload(false);
		} else {
			cargo.setMulUnload(true);
		}
		
		cargo.setQty(loadQty);
		
		return cargo;
	}*/
    

    /**
     * @Desinition:多点卸货和多点装货修改
     * @param:loadingList
     * @param:unloadingList
     * @param:cargoId
     */
	@Deprecated
    public void updateOrSave(List<Loading> loadingList, List<Loading> unloadingList, Integer cargoId) {
        List<Loading> loadings = loadingDao.selectLoadingList(cargoId, CargoConstant.LOADING_TYPE);
        List<Loading> unloadings = loadingDao.selectLoadingList(cargoId, CargoConstant.UNLOADING_TYPE);
        //批量操作
        List<Loading> updateList = new ArrayList<>();
        List<Integer> deleteIds = new ArrayList<>();
        List<Loading> batchAdds = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        getLoadList(loadingList, batchAdds, ids, updateList, CargoConstant.LOADING_TYPE);
        getLoadList(unloadingList, batchAdds, ids, updateList, CargoConstant.UNLOADING_TYPE);
        getDeleteList(loadings, ids, deleteIds);
        getDeleteList(unloadings, ids, deleteIds);
        if (deleteIds != null && deleteIds.size() > 0) { //批量删除
            loadingDao.batchDelete(deleteIds);
        }
        if (batchAdds != null && batchAdds.size() > 0) { //批量添加
            loadingDao.batchAdds(batchAdds, cargoId);
        }
        if (updateList != null && updateList.size() > 0) {//批量修改
            loadingDao.batchUpdate(updateList);
        }
    }
	@Deprecated
    public void getLoadList(List<Loading> list, List<Loading> batchAdds, List<Integer> ids, List<Loading> updateList, String type) {
        if (list != null && list.size() > 0) {
            for (Loading loading : list) {
                if (loading.getId() == null) {
                    loading.setType(type);
                    batchAdds.add(loading);
                } else {
                    updateList.add(loading);
                    ids.add(loading.getId());
                }
            }
        }
    }
	@Deprecated
    public void getDeleteList(List<Loading> list, List<Integer> ids, List<Integer> deleteIds) {
        if (list != null && list.size() > 0) {
            for (Loading load : list) {
                if (!ids.contains(load.getId())) {
                    deleteIds.add(load.getId());
                } else {
                    continue;
                }
            }
        }
    }


    /**
     * Desintion:邀请报价(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
	@Deprecated
    @Override
    public ServerResponse invitationOffer(CargoDto cargoDto) {
        CheckFieldUtils.checkObjecField(cargoDto.getCargoIds());
        CheckFieldUtils.checkObjecField(cargoDto.getCarIds());
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        List<Offer> offerList = getOfferList(cargoDto.getCargoIds(), cargoDto.getCarIds(), sysCustomer.getId());
        if(offerList !=null&& offerList.size()>0) {
            int num = cargoDao.invitationOffer(offerList, CargoConstant.OFFER_PROCESS);
            CheckFieldUtils.assertSuccess(num);
        }
        return ServerResponse.success();
    }

	@Deprecated
    public List<Offer> getOfferList(List<Integer> cargoIds, List<Integer> carIds, Integer createBy) {
        List<Offer> offerList = new ArrayList<>();
        for (Integer cargoId : cargoIds) {
            for (Integer carId : carIds) {
                Offer offer = new Offer();
                offer.setCreateBy(createBy);
                offer.setCargoId(cargoId);
                offer.setCarOwnnerId(carId);
                if(!offerList.contains(offer)){
                    offerList.add(offer);
                }
            }
        }
        return offerList;
    }

    /**
     * Desintion:我的货源分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @Override
    @Deprecated
    public ServerResponse mySourcePage(CargoDto cargoDto, Page page) {
        Cargo cargoCustomer = BeanUtil.copy(cargoDto, Cargo.class);
        String orderField = "create_date";
        PageInfo<Cargo> pageInfo = cargoDao.cargoPage(cargoCustomer, orderField, page);
        return ServerResponse.successWithData(pageInfo);
    }
    
	@Autowired
	private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	public ServerResponse search(Integer pageNum, Integer pageSize, CargoSourceSearchDto dto) {
		com.github.pagehelper.Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<Cargo> list = cargoMapper.cargoSearch(dto);
		for (Cargo cargo : list) {
			String cargoOwnerName = carCargoOwnnerRepository.findNameById(cargo.getCreateBy());
			cargo.setCargoOwnerName(cargoOwnerName);
		}
		PageData<Cargo> pageData = new PageData<>(page.getPageSize(), page.getPageNum(), page.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	@Autowired
	private CargoMatterRepository cargoMatterRepository;
	
	@Override
	public ServerResponse myCargoSourcePage(Integer pageNum, Integer pageSize, CargoSourceSearchDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer id = cargoOwner.getId();
		dto.setCreateBy(id);

		com.github.pagehelper.Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<Cargo> list = cargoMapper.cargoSearch(dto);
		for (Cargo cargo : list) {
			if (cargoOwner.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_PERSON)) {
				cargo.setCargoOwnerName(cargoOwner.getLegalerName());
			} else {
				cargo.setCargoOwnerName(cargoOwner.getName());
			}
			int countByCargoId = cargoMatterRepository.countByCargoId(cargo.getId());
			cargo.setCargoMatterCount(countByCargoId);
			//設置logo為用戶logo
			cargo.setLogo(cargoOwner.getHeadImg());
			
			cargo.setLevelAVG(evaluateService.levelAVG(cargo.getCreateBy()));
		}

		PageData<Cargo> pageData = new PageData<>(page.getPageSize(), page.getPageNum(), page.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	
	
	
	
	public ServerResponse details(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer cargoOwnerId = cargoOwner.getId();
		Cargo cargo = this.assertCargoBelongToCurrentCargoOwner(dto.getId(), cargoOwnerId);
		cargo.setCargoOwnerName(cargoOwner.getName());
		cargo.setCargoOwnerTel(cargoOwner.getTel());
		int countByCargoId = cargoMatterRepository.countByCargoId(cargo.getId());
		cargo.setCargoMatterCount(countByCargoId);
		if (cargo.getStatus().equalsIgnoreCase(CargoConstant.CARGO_SOURCE_STATUS_COMPLETED)) {
			List<CargoMatter> cargoMatters = cargoMatterRepository.findByCargoIdAndStatus(cargo.getId(),
					CargoMatterConstants.CARGO_MATTER_STATUS_TENDER);
			CargoMatter cargoMatter = cargoMatters.get(0);
			Integer carOwnnerId = cargoMatter.getCarOwnnerId();
			CarCargoOwnner carOwner = carCargoOwnnerRepository.findOne(carOwnnerId);
			if (carOwner.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_PERSON)) {
				cargoMatter.setCarOwnerName(carOwner.getLegalerName());
			} else {
				cargoMatter.setCarOwnerName(carOwner.getName());
			}
			cargo.setCargoMatter(cargoMatter);
			//設置logo為用戶logo
			cargo.setLogo(customer.getLogo());
		}
		cargo.setLevelAVG(evaluateService.levelAVG(cargo.getCreateBy()));
		List<Loading> list = cargoLocationRepository.findByCargoId(cargo.getId());
		cargo.setCargoLocations(list);
		return ServerResponse.successWithData(cargo);
	}
    
    @Transactional
	@Override
	public ServerResponse cancel(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer cargoOwnerId = cargoOwner.getId();
		Cargo cargo = this.assertCargoBelongToCurrentCargoOwner(dto.getId(), cargoOwnerId);
		if (CargoConstant.CARGO_SOURCE_STATUS_DOWN.equalsIgnoreCase(cargo.getStatus())) {
			cargoMapper.updateStatusById(cargo.getId(), CargoConstant.CARGO_SOURCE_STATUS_CANCELED);
		} else {
			throw new BusinessException(112, "仅下架状态的货源可以取消");
		}
		return ServerResponse.success();
	}

	@Override
	public Cargo assertCargoAvailable(Integer cargoId) {
		Cargo cargo = cargoRepository.findOne(cargoId);
		if (cargo == null) {
			throw new BusinessException(112, "货源不存在");
		}
		return cargo;
	}


	@Transactional
	@Override
	public ServerResponse submitChcek(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer cargoOwnerId = cargoOwner.getId();
		Cargo cargo = this.assertCargoBelongToCurrentCargoOwner(dto.getId(), cargoOwnerId);
		if (CargoConstant.CARGO_SOURCE_STATUS_DOWN.equalsIgnoreCase(cargo.getStatus())) {
			cargoMapper.updateStatusById(cargo.getId(), CargoConstant.CARGO_SOURCE_STATUS_WAIT_CHECK);
		} else {
			throw new BusinessException(112, "仅下架状态的货源可以提交审核");
		}
		return ServerResponse.success();
	}

	
	@Transactional
	@Override
	public ServerResponse down(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer cargoOwnerId = cargoOwner.getId();
		Cargo cargo = this.assertCargoBelongToCurrentCargoOwner(dto.getId(), cargoOwnerId);
		if (CargoConstant.CARGO_SOURCE_STATUS_WAIT_CHECK.equalsIgnoreCase(cargo.getStatus()) 
				|| CargoConstant.CARGO_SOURCE_STATUS_RELEASE.equalsIgnoreCase(cargo.getStatus())) {
			cargoMapper.updateStatusById(cargo.getId(), CargoConstant.CARGO_SOURCE_STATUS_DOWN);
		} else {
			throw new BusinessException(112, "仅待审核、发布中的货源可以下架");
		}
		return ServerResponse.success();
	}

	@Transactional
	@Override
	public ServerResponse updateNoCheck(CargoSourceNoCheckUpdateDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer cargoOwnerId = cargoOwner.getId();
		Cargo cargo = this.assertCargoBelongToCurrentCargoOwner(dto.getId(), cargoOwnerId);
		
		cargo = new Cargo();
		BeanUtils.copyProperties(dto, cargo);
		
		cargoMapper.updateSource(cargo);
		return ServerResponse.success();
	}

	@Transactional
	@Override
	public ServerResponse updateCheck(CargoSourceCheckUpdateDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer cargoOwnerId = cargoOwner.getId();
		Cargo cargo = this.assertCargoBelongToCurrentCargoOwner(dto.getId(), cargoOwnerId);
		
		if(!CargoConstant.CARGO_SOURCE_STATUS_DOWN.equalsIgnoreCase(cargo.getStatus())) {
			throw new BusinessException(112, "仅下架状态的货源可以修改需要审核的内容");
		}
		
		cargo = new Cargo();
		BeanUtils.copyProperties(dto, cargo);
		
		cargoMapper.updateSource(cargo);
		return ServerResponse.success();
	}

	@Autowired
	private MyFocusPersonService myFocusPersonService;
	
	@Autowired
	private EvaluateService evaluateService;
	
	@Override
	public ServerResponse openPage(Integer pageNum, Integer pageSize, CargoSourceSearchDto dto) {
		dto.setStatus(CargoConstant.CARGO_SOURCE_STATUS_RELEASE);

		com.github.pagehelper.Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<Cargo> list = cargoMapper.cargoSearch(dto);
		for (Cargo cargo : list) {
			CarCargoOwnner cargoOwner = carCargoOwnnerRepository.findOne(cargo.getCreateBy());
			cargo.setCargoOwnerName(cargoOwner.getName());
			cargo.setCargoOwnerTel(cargoOwner.getTel());

			int countByCargoId = cargoMatterRepository.countByCargoId(cargo.getId());
			cargo.setCargoMatterCount(countByCargoId);

			//根据用户id获取用户logo
			//Integer customerId = cargoOwner.getCustomerId();
			//Customer customer = logisticsCustomerRepository.findOne(customerId);
			cargo.setLogo(cargoOwner.getHeadImg());
			
			cargo = this.bindFocusAndOffer(cargo);
			cargo.setLevelAVG(evaluateService.levelAVG(cargo.getCreateBy()));
		}

//		倒过来遍历list,处理抢标的情况
		if(list!=null && list.size()>0){
			for(int i=list.size()-1;i>=0;i--){
				Cargo cargoIn = list.get(i);
				//投标方式为抢标且当前时间 - 创建时间 < 配置时间 , 不展示该结果
				if (cargoIn.getTenderWay().equals(CargoConstant.BID_MARK)
						&&(new Date().getTime() - cargoIn.getCreateDate().getTime()< Long.valueOf(bidWaitTime)*3600*1000) ){
					list.remove(i);
				}
			}
		}


		PageData<Cargo> pageData = new PageData<>(page.getPageSize(), page.getPageNum(), page.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}



	@Override
	public ServerResponse openDetails(CommonDto dto) {
		Cargo cargo = this.assertCargoAvailable(dto.getId());
		if (CargoConstant.CARGO_SOURCE_STATUS_RELEASE.equalsIgnoreCase(cargo.getStatus())
				|| CargoConstant.CARGO_SOURCE_STATUS_COMPLETED.equalsIgnoreCase(cargo.getStatus())) {
			List<Loading> list = cargoLocationRepository.findByCargoId(cargo.getId());
			cargo.setCargoLocations(list);
			Integer createBy = cargo.getCreateBy();
			CarCargoOwnner cargoOwner = carCargoOwnnerRepository.findOne(createBy);
			cargo.setCargoOwnerName(cargoOwner.getName());
			cargo.setCargoOwnerTel(cargoOwner.getTel());

			cargo = this.bindFocusAndOffer(cargo);
			cargo.setLevelAVG(evaluateService.levelAVG(cargo.getCreateBy()));
            //根据用户id获取用户logo
            Integer customerId = cargoOwner.getCustomerId();
            Customer customer = logisticsCustomerRepository.findOne(customerId);
            cargo.setLogo(customer.getLogo());
			return ServerResponse.successWithData(cargo);
		} else {
			throw new BusinessException(112, "仅可查看发布中，已完成货源的详情");
		}
	}
	
	/**
	 * 为货源绑定是否已关注，是否已报价
	 * @author cat
	 * 
	 * @param cargo
	 * @return
	 */
	private Cargo bindFocusAndOffer(Cargo cargo) {
		Integer createBy = cargo.getCreateBy();
		// 如果当前有用户登录，且登录人是车主，则判断是否已关注货源的货主
		try {
			Customer customer = RequestUtil.getCurrentUser(Customer.class);
			if (customer != null) {
				CarCargoOwnner carOwner = customer.getCarOwner();
				if (carOwner != null) {
					Integer carOwnerId = carOwner.getId();

					// 是否已关注
					boolean focus = myFocusPersonService.isFocus(carOwnerId, createBy);
					cargo.setIsFocus(focus);

					// 是否已报价
					List<CargoMatter> temp = cargoMatterRepository.findByCargoIdAndCarOwnnerId(cargo.getId(),
							carOwnerId);
					if (!temp.isEmpty()) {
						cargo.setIsOffer(true);
					} else {
						cargo.setIsOffer(false);
					}
				}
			}
		} catch (Exception e) {
			// nothing to do
		}
		return cargo;
	}

	/**
	 * 推荐货源分页
	 * @param dto
	 * @return
	 */
	public ServerResponse recommendCargo(Integer pageNum, Integer pageSize,CargoRecDto dto) {
		Customer cur_customer = RequestUtil.getCurrentUser(Customer.class);
		//取得车主id
		Integer id = cur_customer.getCarOwner().getId();
		com.github.pagehelper.Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<Cargo> list = cargoMapper.recommendCargo(id);
		for (Cargo cargo : list) {
			CarCargoOwnner cargoOwner = carCargoOwnnerRepository.findOne(cargo.getCreateBy());
			cargo.setCargoOwnerName(cargoOwner.getName());
			cargo.setCargoOwnerTel(cargoOwner.getTel());

			int countByCargoId = cargoMatterRepository.countByCargoId(cargo.getId());
			cargo.setCargoMatterCount(countByCargoId);

			//根据用户id获取用户logo
			Integer customerId = cargoOwner.getCustomerId();
			Customer customer = logisticsCustomerRepository.findOne(customerId);
			cargo.setLogo(customer.getLogo());

			cargo = this.bindFocusAndOffer(cargo);
		}
		PageData<Cargo> pageData = new PageData<>(page.getPageSize(), page.getPageNum(), page.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}

}
