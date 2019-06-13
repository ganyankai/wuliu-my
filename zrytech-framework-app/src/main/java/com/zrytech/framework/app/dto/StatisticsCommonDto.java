package com.zrytech.framework.app.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class StatisticsCommonDto {
    @NotNull(message = "Id不能为空")
    private Integer id;
}
