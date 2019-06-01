package com.zrytech.framework.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 14:47
 **/
@Data
public class WlArticleLikeCollectDto {

    private Integer id;

    private Integer articleId;

    private Integer userId;

    private Byte likeStatus;

    private Byte collectStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;

    /**
     * 标识点赞还是收藏 0 点赞   1 收藏
     */
    private Integer type;

}
