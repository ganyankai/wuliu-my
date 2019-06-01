package com.zrytech.framework.app.service.impl;

//import com.zrytech.framework.base.entity.Customer;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.app.dao.WlArticleDao;
import com.zrytech.framework.app.dao.WlArticleLikeCollectDao;
import com.zrytech.framework.common.dao.UserDao;
import com.zrytech.framework.app.dto.WlArticleLikeCollectDto;
import com.zrytech.framework.app.entity.WlArticle;
import com.zrytech.framework.app.entity.WlArticleLikeCollect;
import com.zrytech.framework.common.entity.SysCustomer;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import com.zrytech.framework.app.service.WlArticleLikeCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 15:05
 **/
@Service
public class WlArticleLikeCollectServiceImpl implements WlArticleLikeCollectService {

    @Autowired
    private WlArticleLikeCollectDao wlArticleLikeCollectDao;

    @Autowired
    private WlArticleDao wlArticleDao;

    @Autowired
    private UserDao userDao;

    public ServerResponse save(WlArticleLikeCollectDto sysWlArticleLikeCollectDto, SysCustomer customer) {
        if(sysWlArticleLikeCollectDto ==null|| sysWlArticleLikeCollectDto.getArticleId()==null){
            throw new BusinessException( new CommonResult( ResultEnum.OBJECT_ERROR ) );
        }
        WlArticle wlArticle = wlArticleDao.selectArticleById( sysWlArticleLikeCollectDto.getArticleId() );
        if(wlArticle ==null){
            throw new BusinessException( new CommonResult( ResultEnum.REPOSITORY_NOT_EXIST ) );
        }
        WlArticleLikeCollect wlArticleLikeCollect = wlArticleLikeCollectDao.queryObject( sysWlArticleLikeCollectDto.getArticleId() );
        WlArticle a=new WlArticle();
        if(wlArticleLikeCollect !=null){
            if(sysWlArticleLikeCollectDto.getType()==0){//点赞
                if(wlArticleLikeCollect.getLikeStatus()==0){ //状态0:表示未点赞,将其改为1
                    wlArticleLikeCollect.setLikeStatus( 1);
                    Integer count= wlArticle.getArticleLikeCount()+1;
                    a.setArticleLikeCount( count );
                    a.setId( wlArticle.getId() );
                    wlArticleLikeCollectDao.updateArticleLikeCollect(wlArticleLikeCollect);
                    wlArticleDao.updateArticle( a );
                }else {                           //状态1:表示已点赞,再点就是取消点赞
                    wlArticleLikeCollect.setLikeStatus( 0 );
                    Integer count= wlArticle.getArticleLikeCount()-1;
                    a.setArticleLikeCount( count );
                    a.setId( wlArticle.getId() );
                    wlArticleLikeCollectDao.updateArticleLikeCollect(wlArticleLikeCollect);
                    wlArticleDao.updateArticle( a );
                }
            }else {//收藏
                if(wlArticleLikeCollect.getCollectStatus()==0){ //0:表示未收藏,点击收藏
                    wlArticleLikeCollect.setCollectStatus( 1 );
                    Integer count= wlArticle.getArticleCollectCount()+1;
                    a.setArticleCollectCount( count );
                    a.setId( wlArticle.getId() );
                    wlArticleLikeCollectDao.updateArticleLikeCollect(wlArticleLikeCollect);
                    wlArticleDao.updateArticle( a );
                }else {  //1:表示已收藏,再点取消收藏
                    wlArticleLikeCollect.setCollectStatus( 0 );
                    Integer count= wlArticle.getArticleCollectCount()-1;
                    a.setArticleCollectCount( count );
                    a.setId( wlArticle.getId() );
                    wlArticleLikeCollectDao.updateArticleLikeCollect(wlArticleLikeCollect);
                    wlArticleDao.updateArticle( a );
                }
            }
        }else {
            wlArticleLikeCollect =new WlArticleLikeCollect();
            if(sysWlArticleLikeCollectDto.getType()==0){//点赞
                wlArticleLikeCollect.setArticleId( sysWlArticleLikeCollectDto.getArticleId() );
                wlArticleLikeCollect.setUserId( customer.getId() );
                wlArticleLikeCollect.setLikeStatus( 1);
                wlArticleLikeCollect.setCollectStatus( 0);
                Integer count= wlArticle.getArticleLikeCount()+1;
                a.setArticleLikeCount( count );
                a.setId( wlArticle.getId() );
                wlArticleLikeCollectDao.insertArticleLikeCollect(wlArticleLikeCollect);
                wlArticleDao.updateArticle( a );
            }else {//收藏
                wlArticleLikeCollect.setArticleId( sysWlArticleLikeCollectDto.getArticleId() );
                wlArticleLikeCollect.setUserId( customer.getId() );
                wlArticleLikeCollect.setLikeStatus( 0 );
                wlArticleLikeCollect.setCollectStatus( 1);
                Integer count= wlArticle.getArticleCollectCount()+1;
                a.setArticleCollectCount( count );
                a.setId( wlArticle.getId() );
                wlArticleLikeCollectDao.insertArticleLikeCollect(wlArticleLikeCollect);
                wlArticleDao.updateArticle( a );
            }
        }
        return ServerResponse.success();
    }
}
