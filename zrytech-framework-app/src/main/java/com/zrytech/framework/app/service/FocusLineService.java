package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.FocusLineDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

public interface FocusLineService {
    ServerResponse linePage(FocusLineDto focusLineDto, Page page);

    ServerResponse get(FocusLineDto focusLineDto);

    ServerResponse delete(FocusLineDto focusLineDto);

    ServerResponse add(FocusLineDto focusLineDto);
}
