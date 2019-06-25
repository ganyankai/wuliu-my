package com.zrytech.framework.app.dto;

import com.zrytech.framework.app.dto.cargolocation.CargoLocationAddDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Data
public class MessageReadDto {
    @NotNull(message = "id集合不能为空")
    @Size(min = 1, message = "至少有一个id")
    private List<Integer> idsList;

}
