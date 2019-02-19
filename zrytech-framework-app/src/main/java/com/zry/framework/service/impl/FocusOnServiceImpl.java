package com.zry.framework.service.impl;

import com.github.pagehelper.PageInfo;
import com.zry.framework.dao.FocusLineDao;
import com.zry.framework.dao.FocusOnDao;
import com.zry.framework.dto.FocusDto;
import com.zry.framework.entity.Focus;
import com.zry.framework.entity.FocusLine;
import com.zry.framework.service.FocusOnService;
import com.zry.framework.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.BeanUtil;
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

    @Override
    public ServerResponse focusPage(FocusDto focusDto, Page page) {
        Focus focus = BeanUtil.copy(focusDto, Focus.class);
        PageInfo<Focus> pageInfo = focusOnDao.focusPage(focus, page);
        return ServerResponse.successWithData(pageInfo);
    }

    @Override
    public ServerResponse get(FocusDto focusDto) {
        Focus focus = focusOnDao.get(focusDto.getId());
        return ServerResponse.successWithData(focus);
    }

    @Override
    public ServerResponse delete(FocusDto focusDto) {
        int num = focusOnDao.delete(focusDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse add(FocusDto focusDto) {
        Focus focus = BeanUtil.copy(focusDto, Focus.class);
        focus.setCreateDate(new Date());
        //TODO:focus.setFocuserId();设置关注人
        //TODO: focus.setFocusType();设置关注类型
        int num = focusOnDao.add(focus);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse selectMyFocus(FocusDto focusDto) {
        CheckFieldUtils.checkObjecField(focusDto.getFocuserId());//TODO:获取当前登录用户ID
        List<FocusLine> focusLineList=focusLineDao.selectCreateBy(focusDto.getFocuserId());
        List<Focus> focusList=focusOnDao.selectCreateBy(focusDto.getFocuserId());
        Map map=new HashMap();
        map.put("linList",focusLineList);
        map.put("focusList",focusList);
        return ServerResponse.successWithData(map);
    }
}