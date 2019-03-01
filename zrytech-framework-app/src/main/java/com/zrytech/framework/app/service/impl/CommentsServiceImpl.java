package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.CommentsDao;
import com.zrytech.framework.app.dto.EvaluationDto;
import com.zrytech.framework.app.entity.Evaluation;
import com.zrytech.framework.app.service.CommentsService;
import com.zrytech.framework.app.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsDao commentsDao;

    @Override
    public ServerResponse evaluationPage(EvaluationDto evaluationDto, Page page) {
        Evaluation evaluation= BeanUtil.copy(evaluationDto,Evaluation.class);
        PageInfo<Evaluation> pageInfo=commentsDao.evaluationPage(evaluation,page);
        return ServerResponse.successWithData(pageInfo);
    }

    @Override
    public ServerResponse get(EvaluationDto evaluationDto) {
        Evaluation evaluation=commentsDao.get(evaluationDto.getId());
        return ServerResponse.successWithData(evaluation);
    }

    @Override
    public ServerResponse delete(EvaluationDto evaluationDto) {
        int num=commentsDao.delete(evaluationDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    @Override
    public int addComments(Evaluation evaluation) {
        return commentsDao.addComments(evaluation);
    }
}
