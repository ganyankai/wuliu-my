package com.zrytech.framework.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.entity.Evaluation;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;

public interface CommentsMapper {
    PageInfo<Evaluation> evaluationPage(@Param("evaluation") Evaluation evaluation, Page page);

    Evaluation get(@Param("id") Integer id);

    int delete(@Param("id") Integer id);

    int addComments(Evaluation evaluation);
}
