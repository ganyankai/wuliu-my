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
@ApiModel(value = "article数据对象")
public class WlArticleDto implements Serializable {

    @ApiModelProperty(value = "主键", required = false)
    private Integer id;

    @ApiModelProperty(value = "分类ID", required = false)
    private Integer articleCategoryId;

    @ApiModelProperty(value = "文字标题", required = false)
    private String articleTitle;

    @ApiModelProperty(value = "文字封面", required = false)
    private Integer articleCover;

    @ApiModelProperty(value = "文字的点赞数", required = false)
    private Integer articleLikeCount;

    @ApiModelProperty(value = "文字的收藏数", required = false)
    private Integer articleCollectCount;

    @ApiModelProperty(value = "创建人", required = false)
    private Integer articleCreateUserId;

    @ApiModelProperty(value = "文字的状态", required = false)
    private Integer articleStatus;

    @ApiModelProperty(value = "是否置顶，1.置顶，0.不置顶", required = false)
    private Integer top;

    @ApiModelProperty(value = "文字的内容", required = false)
    private String articleContent;

    @ApiModelProperty(value = "创建时间", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "文字的附件id", required = false)
    private String attachIds;

    @ApiModelProperty(value = "附件名称", required = false)
    private String attachNames;
    
    @ApiModelProperty(value = "远程地址", required = false)
    private String sourceUrl;
    
}
