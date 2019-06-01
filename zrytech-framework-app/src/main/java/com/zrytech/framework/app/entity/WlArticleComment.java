package com.zrytech.framework.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrytech.framework.app.utils.WlAttUtils;
import com.zrytech.framework.base.constant.DictConstant;
import com.zrytech.framework.common.util.DictionUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

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
@ApiModel(value = "articleComment对象")
public class WlArticleComment implements Serializable {

    @ApiModelProperty(value = "主键", required = false)
    private Integer id;

    @ApiModelProperty(value = "文字ID", required = false)
    private Integer articleId;

    private String shortName;

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
    private String commentUserType;

    @ApiModelProperty(value = "被评论人的类型", required = false)
    private String commentParentUserType;

    @ApiModelProperty(value = "创建时间", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private String replyName;

    private String headImg;

    private String headImgUrl;

    private String commentUserType_s;

    private String commentParentUserType_s;

    public String getHeadImgUrl() {
        if (!StringUtils.isBlank(headImg)) {
            return WlAttUtils.getUrlPath(headImg);
        }
        return headImgUrl;
    }

    public String getCommentUserType_s() {
        if (StringUtils.isNotEmpty(commentUserType)) {
            return DictionUtil.getValue(DictConstant.COMMENT_USER_TYPE, commentUserType);
        }
        return commentUserType_s;
    }

    public String getCommentParentUserType_s() {
        if (StringUtils.isNotEmpty(commentParentUserType)) {
            return DictionUtil.getValue(DictConstant.COMMENT_USER_TYPE, commentParentUserType);
        }
        return commentParentUserType_s;
    }

}
