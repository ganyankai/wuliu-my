package com.zry.framework.service.impl;

import com.zry.framework.dao.CommentsDao;
import com.zry.framework.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsDao commentsDao;
}
