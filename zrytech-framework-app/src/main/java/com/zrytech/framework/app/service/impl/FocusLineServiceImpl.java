package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.FocusLineDao;
import com.zrytech.framework.app.dto.FocusLineDto;
import com.zrytech.framework.app.entity.FocusLine;
import com.zrytech.framework.app.service.FocusLineService;
import com.zrytech.framework.app.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FocusLineServiceImpl implements FocusLineService {

    @Autowired
    private FocusLineDao focusLineDao;

    /**
     * Desintion:路线分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:FocusLineDto路线dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse linePage(FocusLineDto focusLineDto, Page page) {
        FocusLine focusLine= BeanUtil.copy(focusLineDto,FocusLine.class);
        PageInfo<FocusLine> pageInfo=focusLineDao.linePage(focusLine,page);
        return ServerResponse.successWithData(pageInfo);
    }

    /**
     * Desintion:路线详情
     *
     * @author:jiangxiaoxiang
     * @param:FocusLineDto路线dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse get(FocusLineDto focusLineDto) {
        FocusLine focusLine=focusLineDao.get(focusLineDto.getId());
        return ServerResponse.successWithData(focusLine);
    }

    /**
     * Desintion:删除路线
     *
     * @author:jiangxiaoxiang
     * @param:FocusLineDto路线dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse delete(FocusLineDto focusLineDto) {
        int num=focusLineDao.delete(focusLineDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * Desintion:添加路线
     *
     * @author:jiangxiaoxiang
     * @param:FocusLineDto路线dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse add(FocusLineDto focusLineDto) {
        FocusLine focusLine=BeanUtil.copy(focusLineDto,FocusLine.class);
        focusLine.setCreateDate(new Date());
        //TODO:focusLine.setCreateBy();
        int num=focusLineDao.addFocusLine(focusLine);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }
}
