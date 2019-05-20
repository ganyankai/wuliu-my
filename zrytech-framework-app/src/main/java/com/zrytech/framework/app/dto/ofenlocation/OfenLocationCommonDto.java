package com.zrytech.framework.app.dto.ofenlocation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
public class OfenLocationCommonDto {
    @NotNull(message = "Id不能为空")
    private Integer id;
}
