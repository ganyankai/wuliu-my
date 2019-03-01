package com.zrytech.framework.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ApiModel(value = "货物装卸地dto")
public class LoadingDto implements Serializable {
    private static final long serialVersionUID = -1555792098489335740L;

    @ApiModelProperty(value = "主键id", required = false)
    private Integer id;

    @ApiModelProperty(value = "货源Id", required = false)
    private Integer cargoId;

    @ApiModelProperty(value = "经度", required = false)
    private Double longitude;

    @ApiModelProperty(value = "纬度", required = false)
    private Double latitude;

    @ApiModelProperty(value = "省", required = false)
    private String province;

    @ApiModelProperty(value = "市", required = false)
    private String city;

    @ApiModelProperty(value = "县", required = false)
    private String county;

    @ApiModelProperty(value = "详细地址", required = false)
    private String addressDetail;

    @ApiModelProperty(value = "装卸数量", required = false)
    private Integer qty;

    @ApiModelProperty(value = "类型", required = false)
    private String type;

    @ApiModelProperty(value = "备注", required = false)
    private String remark;

    @ApiModelProperty(value = "序列", required = false)
    private Integer seqNo;

    @ApiModelProperty(value = "状态", required = false)
    private String status;

    @ApiModelProperty(value = "装卸日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loadDate;

    @ApiModelProperty(value = "截止日期", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;

}
