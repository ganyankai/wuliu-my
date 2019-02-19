package com.zry.framework.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zry.framework.dao.CommentsDao;
import com.zry.framework.entity.Evaluation;
import com.zry.framework.mapper.CommentsMapper;
import com.zrytech.framework.base.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CommentsDaoImpl implements CommentsDao {

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public PageInfo<Evaluation> evaluationPage(Evaluation evaluation, Page page) {
        if(page==null){
            page=new Page();
        }
        return commentsMapper.evaluationPage(evaluation,page);
    }

    @Override
    public Evaluation get(Integer id) {
        return commentsMapper.get(id);
    }

    @Override
    public int delete(Integer id) {
        return commentsMapper.delete(id);
    }

    @Override
    public int addComments(Evaluation evaluation) {
        return commentsMapper.addComments(evaluation);
    }
}
