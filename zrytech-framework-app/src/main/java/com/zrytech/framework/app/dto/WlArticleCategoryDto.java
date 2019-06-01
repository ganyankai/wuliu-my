package com.zrytech.framework.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: zrytech-framework-parent
 * @description: 
 * @author: LiuChao
 * @create: 2018-05-03 14:40
 **/
@Data
@ApiModel(value = "articleCategory数据对象")
public class WlArticleCategoryDto implements Serializable {

    @ApiModelProperty(value = "主键", required = false)
    private Integer id;

    @ApiModelProperty(value = "父ID", required = false)
    private Integer parentId;

    @ApiModelProperty(value = "分类名称", required = false)
    private String articleCategoryName;

    @ApiModelProperty(value = "分类描述", required = false)
    private String articleCategoryDesc;

    @ApiModelProperty(value = "分类状态", required = false)
    private String articleCategoryStatus;

    @ApiModelProperty(value = "创建时间", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;
        
}
