package com.zrytech.framework.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: zrytech-framework-parent
 * @description:
 * @author: LiuChao
 * @create: 2018-05-03 14:40
 **/
@Setter
@Getter
@ApiModel(value = "articleComment数据对象")
public class WlArticleCommentDto implements Serializable {

    @ApiModelProperty(value = "主键", required = false)
    private Integer id;

    @ApiModelProperty(value = "文章ID", required = false)
    private Integer articleId;

    @ApiModelProperty(value = "评论人ID", required = false)
    private Integer articleCommentUserId;

    @ApiModelProperty(value = "评论内容", required = false)
    private String articleCommentContent;

    @ApiModelProperty(value = "目标评论ID", required = false)
    private Integer articleCommentParentId;

    @ApiModelProperty(value = "目标评论人ID", required = false)
    private Integer articleCommentParentUserId;

    @ApiModelProperty(value = "是否被查看，1.被查看，0.没有查看", required = false)
    private Integer articleCommentViewed;

    @ApiModelProperty(value = "状态", required = false)
    private String articleCommentStatus;

    @ApiModelProperty(value = "评论人类型", required = false)
    private Byte commentUserType;

    @ApiModelProperty(value = "被评论人的类型", required = false)
    private Byte commentParentUserType;

    private String shortName;

    @ApiModelProperty(value = "创建时间", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "评论的人数", required = false)
    private Long articleCommentUserNm;

    @ApiModelProperty(value = "评论人头像", required = false)
    private Long articleCommentUserIcon;

    @ApiModelProperty(value = "被评论人的用户数", required = false)
    private Long articleCommentUserParentNm;

    @ApiModelProperty(value = "被评论人头像", required = false)
    private Long articleCommentUserParentIcon;

        
}
