package com.zry.framework.service;

import com.zry.framework.dto.MessageDto;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

public interface MessageService {
    ServerResponse messagePage(MessageDto messageDto, Page page);

    ServerResponse get(MessageDto messageDto);

    ServerResponse addMessage(MessageDto messageDto);

    ServerResponse delete(MessageDto messageDto);

    ServerResponse selectMsgList(MessageDto messageDto);

    ServerResponse selectTypePage(MessageDto messageDto, Page page);
}
