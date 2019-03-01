package com.zrytech.framework.app.utils;

import com.zrytech.framework.app.enums.LogisticsResult;
import com.zrytech.framework.app.enums.LogisticsResultEnum;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import org.apache.commons.lang3.StringUtils;

public class CheckFieldUtils {

    public static void checkObjecField(Object field) {
        if (field == null) {
            throw new BusinessException(new CommonResult(ResultEnum.PARAMETER_ERROR));
        }
        if (field != null && StringUtils.isBlank(field.toString())) {
            throw new BusinessException(new CommonResult(ResultEnum.PARAMETER_ERROR));
        }
    }

    public static void assertSuccess(int num) {
        if (num <= 0) {
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.FAIL));
        }
    }
}
