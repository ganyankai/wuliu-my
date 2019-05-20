package com.zrytech.framework.app.dto.ofenlocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
@Setter
@Getter
public class OfenLocationDto {
    @ApiModelProperty(value = "主键id", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "联系人名称", required = false)
    private String name;

    @ApiModelProperty(value = "联系人手机号", required = false)
    private String tel;

    @ApiModelProperty(value = "省", required = false)
    private String province;

    @ApiModelProperty(value = "市", required = false)
    private String city;

    //--
    @ApiModelProperty(value = "县", required = false)
    private String county;

    @ApiModelProperty(value = "详细地址", required = false)
    private String detailedAddress;

    /** 经度 */
    private BigDecimal longitude;

    /** 纬度 */
    private BigDecimal latitude;

    @ApiModelProperty(value = "创建时间", required = false, example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String createDate;

    //--
    @ApiModelProperty(value = "货主Id", required = false)
    private Integer cargoOwnerId;

}
