package com.zrytech.framework.app.dto.ofenlocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
public class OfenLocationAddDto {

    private Integer id;

    @ApiModelProperty(value = "联系人名称", required = false)
    @NotBlank(message = "联系人名称不能为空")
    private String name;

    @ApiModelProperty(value = "联系人手机号", required = false)
    @NotBlank(message = "联系人手机号不能为空")
    private String tel;

    @ApiModelProperty(value = "省", required = false)
    @NotBlank(message = "省不能为空")
    private String province;

    @ApiModelProperty(value = "市", required = false)
    @NotBlank(message = "市不能为空")
    private String city;

    //--
    @ApiModelProperty(value = "县", required = false)
    @NotBlank(message = "县不能为空")
    private String county;

    @ApiModelProperty(value = "详细地址", required = false)
    @NotBlank(message = "详细地址不能为空")
    private String detailedAddress;

    @NotNull(message = "经度不能为空")
    /** 经度 */
    private BigDecimal longitude;

    @NotNull(message = "纬度不能为空")
    /** 纬度 */
    private BigDecimal latitude;


}
