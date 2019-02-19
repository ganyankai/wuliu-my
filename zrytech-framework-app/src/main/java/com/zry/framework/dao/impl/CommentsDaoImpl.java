package com.zry.framework.dao.impl;

import com.zry.framework.dao.CommentsDao;
import com.zry.framework.mapper.CommentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CommentsDaoImpl implements CommentsDao {

    @Autowired
    private CommentsMapper commentsMapper;
}
