package com.zrytech.framework.app.utils;

import com.zrytech.framework.common.util.AttachmentUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WlAttUtils {


    private static AttachmentUtils attachmentUtils;

    @Autowired
    public void setDictUtil(AttachmentUtils attachmentUtils) {
        this.attachmentUtils = attachmentUtils;
    }

    public static String getUrlPath(String attacheIds) {
        if (StringUtils.isBlank(attacheIds)) {
            return null;
        }
        return attachmentUtils.getUrlPath(attacheIds);
    }
}
