package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.EvaluationDto;
import com.zrytech.framework.app.entity.Evaluation;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

public interface CommentsService {
    /**
     * Desintion:评论分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:EvaluationDto评论dto
     * @return:ServerResponse
     */
    ServerResponse evaluationPage(EvaluationDto evaluationDto, Page page);

    /**
     * Desintion:评论详情
     *
     * @author:jiangxiaoxiang
     * @param:EvaluationDto评论dto
     * @return:ServerResponse
     */
    ServerResponse get(EvaluationDto evaluationDto);

    /**
     * Desintion:评论删除
     *
     * @author:jiangxiaoxiang
     * @param:EvaluationDto评论dto
     * @return:ServerResponse
     */
    ServerResponse delete(EvaluationDto evaluationDto);
    /**
     * Desintion:添加评论
     *
     * @author:jiangxiaoxiang
     * @param:Evaluation评论entity
     * @return:int
     */
    int addComments(Evaluation evaluation);
}
