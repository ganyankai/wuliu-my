package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.FocusLineDao;
import com.zrytech.framework.app.dao.FocusOnDao;
import com.zrytech.framework.app.dto.FocusDto;
import com.zrytech.framework.app.entity.Focus;
import com.zrytech.framework.app.entity.FocusLine;
import com.zrytech.framework.app.service.FocusOnService;
import com.zrytech.framework.app.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.BeanUtil;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.SysCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FocusOnServiceImpl implements FocusOnService {

    @Autowired
    private FocusOnDao focusOnDao;

    @Autowired
    private FocusLineDao focusLineDao;

    /**
     * Desintion:关注分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse focusPage(FocusDto focusDto, Page page) {
        Focus focus = BeanUtil.copy(focusDto, Focus.class);
        PageInfo<Focus> pageInfo = focusOnDao.focusPage(focus, page);
        return ServerResponse.successWithData(pageInfo);
    }

    /**
     * Desintion:关注详情
     *
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse get(FocusDto focusDto) {
        Focus focus = focusOnDao.get(focusDto.getId());
        return ServerResponse.successWithData(focus);
    }

    /**
     * Desintion:删除关注
     *
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse delete(FocusDto focusDto) {
        int num = focusOnDao.delete(focusDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * Desintion:添加关注
     *
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse add(FocusDto focusDto) {
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        Focus focus = BeanUtil.copy(focusDto, Focus.class);
        focus.setCreateDate(new Date());
//        focus.setFocuserId(sysCustomer.getId());
        focus.setFocuserId(focusDto.getId());
        //TODO: focus.setFocusType();设置关注类型
        int num = focusOnDao.add(focus);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * Desintion:我的关注
     * @author:jiangxiaoxiang
     * @param:FocusDto关注dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse selectMyFocus(FocusDto focusDto) {
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        //CheckFieldUtils.checkObjecField(focusDto.getFocuserId());
//        focusDto.setFocuserId(sysCustomer.getId());
        focusDto.setFocuserId(focusDto.getFocuserId());
        List<FocusLine> focusLineList=focusLineDao.selectCreateBy(focusDto.getFocuserId());
        List<Focus> focusList=focusOnDao.selectCreateBy(focusDto.getFocuserId());
        Map map=new HashMap();
        map.put("linList",focusLineList);
        map.put("focusList",focusList);
        return ServerResponse.successWithData(map);
    }
}
