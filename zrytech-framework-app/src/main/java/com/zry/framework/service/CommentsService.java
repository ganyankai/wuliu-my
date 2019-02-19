package com.zry.framework.service;

import com.zry.framework.dto.EvaluationDto;
import com.zry.framework.entity.Evaluation;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

public interface CommentsService {
    ServerResponse evaluationPage(EvaluationDto evaluationDto, Page page);

    ServerResponse get(EvaluationDto evaluationDto);

    ServerResponse delete(EvaluationDto evaluationDto);

    int addComments(Evaluation evaluation);
}
