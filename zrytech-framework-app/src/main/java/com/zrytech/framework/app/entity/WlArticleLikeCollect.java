package com.zrytech.framework.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 14:34
 **/
@Data
public class WlArticleLikeCollect {

    private Integer id;

    private Integer articleId;

    private Integer userId;

    private Integer likeStatus;

    private Integer collectStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;

}
