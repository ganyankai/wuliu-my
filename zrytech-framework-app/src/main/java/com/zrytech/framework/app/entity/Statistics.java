package com.zrytech.framework.app.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "统计entry")
public class Statistics {
    //定位中心的数量
    private Integer locCarCount;
    //推荐货源的数量
    private Integer recCargoCount;
    //关注货主的数量
    private Integer followCargoOwnerCount;
    //关注车主的数量
    private Integer followCarOwnerCount;
    //熟车的数量
    private Integer fimiliarCarCount;


}
