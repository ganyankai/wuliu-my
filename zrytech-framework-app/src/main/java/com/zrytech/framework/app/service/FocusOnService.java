package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.FocusDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

public interface FocusOnService {
    ServerResponse focusPage(FocusDto focusDto, Page page);

    ServerResponse get(FocusDto focusDto);

    ServerResponse delete(FocusDto focusDto);

    ServerResponse add(FocusDto focusDto);

    ServerResponse selectMyFocus(FocusDto focusDto);
}
