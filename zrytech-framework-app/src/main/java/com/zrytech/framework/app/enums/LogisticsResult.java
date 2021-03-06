package com.zrytech.framework.app.enums;

import com.zrytech.framework.base.exception.ResultAdapter;

public class LogisticsResult implements ResultAdapter {

    private LogisticsResultEnum logisticsResultEnum;

    public LogisticsResult(LogisticsResultEnum logisticsResultEnum) {
        this.logisticsResultEnum = logisticsResultEnum;
    }

    @Override
    public int getCode() {
        return logisticsResultEnum.getCode();
    }

    @Override
    public String getMsg() {
        return logisticsResultEnum.getMsg();
    }
}
