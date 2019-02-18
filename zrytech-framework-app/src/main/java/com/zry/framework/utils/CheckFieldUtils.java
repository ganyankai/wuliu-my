package com.zry.framework.utils;

import com.zry.framework.enums.LogisticsResult;
import com.zry.framework.enums.LogisticsResultEnum;
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
