package com.zrytech.framework.app.dto.oftenaddress;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Setter
@Getter
@ApiModel(value = "常用地址查询dto")
public class OftenAddressQueryDto implements Serializable {

    private static final long serialVersionUID = -1555792098489335740L;

    @NotNull(message = "id不能为空")
    private Integer id;
    
}
