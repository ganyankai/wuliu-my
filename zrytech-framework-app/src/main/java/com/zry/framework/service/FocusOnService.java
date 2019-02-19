package com.zry.framework.service;

import com.zry.framework.dto.FocusDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

public interface FocusOnService {
    ServerResponse focusPage(FocusDto focusDto, Page page);

    ServerResponse get(FocusDto focusDto);

    ServerResponse delete(FocusDto focusDto);

    ServerResponse add(FocusDto focusDto);

    ServerResponse selectMyFocus(FocusDto focusDto);
}
