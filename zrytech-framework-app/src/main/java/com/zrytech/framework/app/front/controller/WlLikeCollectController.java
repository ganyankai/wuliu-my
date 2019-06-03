package com.zrytech.framework.app.front.controller;


import com.zrytech.framework.app.dto.WlArticleLikeCollectDto;
import com.zrytech.framework.app.service.WlArticleLikeCollectService;
import com.zrytech.framework.base.annotation.CurrentCustomer;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.dto.ArticleLikeCollectDto;
import com.zrytech.framework.common.entity.SysCustomer;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import com.zrytech.framework.app.service.WlArticleLikeCollectService;
//import com.zrytech.framework.price.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:点赞与收藏
 * @author: jxx
 * @create: 2018/08/21 16:56
 */
@RestController
@RequestMapping("/api/articlelikecollect")
public class WlLikeCollectController {


    @Autowired
    private WlArticleLikeCollectService articleLikeCollectService;

    /**
     * 添加点赞收藏
     *
     * @param:ArticleLikeCollectDto
     * @param:customer
     * @return:ServerResponse
     */
    @RequestMapping("/likeCollect")
    public ServerResponse save(@RequestBody RequestParams<WlArticleLikeCollectDto> params, @CurrentCustomer SysCustomer customer) {
        if (customer == null || customer.getId() == 0) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return articleLikeCollectService.save(params.getParams(),customer);
    }

}
