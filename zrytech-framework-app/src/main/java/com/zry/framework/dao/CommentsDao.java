package com.zry.framework.dao;

import com.github.pagehelper.PageInfo;
import com.zry.framework.entity.Evaluation;
import com.zrytech.framework.base.entity.Page;

public interface CommentsDao {
    PageInfo<Evaluation> evaluationPage(Evaluation evaluation, Page page);

    Evaluation get(Integer id);

    int delete(Integer id);

    int addComments(Evaluation evaluation);

}
