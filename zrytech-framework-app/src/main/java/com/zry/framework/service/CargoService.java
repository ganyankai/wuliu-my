package com.zry.framework.service;

import com.zry.framework.dto.CargoDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

public interface CargoService {
    ServerResponse cargoPage(CargoDto cargoDto, Page page);

    ServerResponse get(CargoDto cargoDto);

    ServerResponse auditSource(CargoDto cargoDto,User user);

    ServerResponse pushResource(CargoDto cargoDto);

    ServerResponse updateSource(CargoDto cargoDto);

    ServerResponse deleteSource(CargoDto cargoDto);

    ServerResponse invitationOffer(CargoDto cargoDto);

    ServerResponse mySourcePage(CargoDto cargoDto, Page page);
}
