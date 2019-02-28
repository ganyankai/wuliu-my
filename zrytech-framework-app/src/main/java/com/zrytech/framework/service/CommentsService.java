package com.zrytech.framework.service;

import com.zrytech.framework.dto.EvaluationDto;
import com.zrytech.framework.entity.Evaluation;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

public interface CommentsService {
    ServerResponse evaluationPage(EvaluationDto evaluationDto, Page page);

    ServerResponse get(EvaluationDto evaluationDto);

    ServerResponse delete(EvaluationDto evaluationDto);

    int addComments(Evaluation evaluation);
}
