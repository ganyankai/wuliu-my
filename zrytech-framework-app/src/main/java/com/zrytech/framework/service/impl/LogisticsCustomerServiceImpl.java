package com.zrytech.framework.service.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.constants.CargoConstant;
import com.zrytech.framework.dao.CargoCustomerDao;
import com.zrytech.framework.dao.ShipperDao;
import com.zrytech.framework.dto.CargoCustomerDto;
import com.zrytech.framework.entity.CargoCustomer;
import com.zrytech.framework.entity.Certification;
import com.zrytech.framework.enums.LogisticsResult;
import com.zrytech.framework.enums.LogisticsResultEnum;
import com.zrytech.framework.utils.CheckFieldUtils;
import com.zrytech.framework.utils.PasswordUtils;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.zrytech.framework.dto.CustomerPageDto;
import com.zrytech.framework.entity.Customer;
import com.zrytech.framework.repository.LogisticsCustomerRepository;
import com.zrytech.framework.service.CustomerService;

import java.util.Date;
import java.util.List;

@Service
public class LogisticsCustomerServiceImpl implements CustomerService {

    @Autowired
    private LogisticsCustomerRepository customerRepository;


    /**
     * @param pageNumber
     * @param pageSize
     * @param dto
     * @return
     */
    public Page<Customer> page(Integer pageNumber, Integer pageSize, CustomerPageDto dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);

        Sort sort = new Sort(Direction.DESC, "createDate");

        Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("carType", GenericPropertyMatchers.contains());

        Example<Customer> example = Example.of(customer, matcher);

        return customerRepository.findAll(example, pageable);
    }

    @Autowired
    private CargoCustomerDao cargoCustomerDao;

    @Autowired
    private ShipperDao shipperDao;

    /**
     * @Desinition:子账号类列表展示
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    @Override
    public ServerResponse childAccountPage(CargoCustomerDto cargoCustomerDto, com.zrytech.framework.base.entity.Page page) {
        CheckFieldUtils.checkObjecField(cargoCustomerDto.getId());
        List<Integer> childCustomerIds = cargoCustomerDao.selectChildListIds(cargoCustomerDto.getId());
		/*List<CargoCustomer> cargoCustomerList=new ArrayList<>();
		if(childCustomerIds !=null&& childCustomerIds.size()>0) {
			cargoCustomerList = cargoCustomerDao.selectChildCustomerList(childCustomerIds);
		}*/
        PageInfo<CargoCustomer> pageInfo = cargoCustomerDao.selectChildCustomerList(childCustomerIds, page);
        return ServerResponse.successWithData(pageInfo);
    }

    /**
     * @Desinition:子账号添加
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    @Override
    public ServerResponse addAccount(CargoCustomerDto cargoCustomerDto) {
        Certification certification = shipperDao.getCustomerId(cargoCustomerDto.getCreateBy());//企业认证同时是认证通过的用户才有权限添加子账号
        if (certification != null && certification.getStatus() != null && CargoConstant.AUDIT_PASS.equalsIgnoreCase(certification.getStatus()) &&
                CargoConstant.CERTIFICATION_ORGANIZE.equalsIgnoreCase(certification.getCustomerType())) {
            //TODO:添加子账号并授权
            CargoCustomer cargoCustomer = BeanUtil.copy(cargoCustomerDto, CargoCustomer.class);
            cargoCustomer.setCreateDate(new Date());
            cargoCustomer.setPwd(PasswordUtils.encryptStringPassword(cargoCustomer.getPwd(), cargoCustomer.getLoginCounter()));
            cargoCustomerDao.insertCustomer(cargoCustomer);
            return ServerResponse.success();
        } else {
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.PERMISSED_NOT_FAIL));
        }
    }
    /**
     * @Desinition:子账号详情
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    @Override
    public ServerResponse detail(CargoCustomerDto cargoCustomerDto) {
        CargoCustomer cargoCustomer = cargoCustomerDao.id(cargoCustomerDto.getId());
        return ServerResponse.successWithData(cargoCustomer);
    }

    /**
     * @Desinition:子账号修改
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    @Override
    public ServerResponse updateAccount(CargoCustomerDto cargoCustomerDto) {
        CargoCustomer cargoCustomer = cargoCustomerDao.id(cargoCustomerDto.getId());
        String newPassword=PasswordUtils.encryptStringPassword(cargoCustomerDto.getPwd(),cargoCustomerDto.getLoginCounter());
        if(!newPassword.equalsIgnoreCase(cargoCustomer.getPwd())){
            cargoCustomerDto.setPwd(newPassword);
        }
        CargoCustomer customer=BeanUtil.copy(cargoCustomerDto,CargoCustomer.class);
        cargoCustomerDao.editChildCustomer(customer);
        return ServerResponse.success();
    }

    /**
     * @Desinition:子账号删除
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    @Override
    public ServerResponse deleteAccount(CargoCustomerDto cargoCustomerDto) {
        int num=cargoCustomerDao.deleteAccount(cargoCustomerDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }
}
