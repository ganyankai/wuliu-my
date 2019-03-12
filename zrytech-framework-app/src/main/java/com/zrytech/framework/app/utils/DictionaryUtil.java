package com.zrytech.framework.app.utils;

import com.zrytech.framework.common.util.DictUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: LiuChao
 * @create: 2018/08/08 16:12
 */
@Component
public class DictionaryUtil {

    private static DictUtil dictUtil;

    @Autowired
    public void setDictUtil(DictUtil dictUtil) {
        this.dictUtil = dictUtil;
    }
    
    public static String getValue(String dictCategoryCode,String dictKey) {
        if (StringUtils.isEmpty(dictCategoryCode) || StringUtils.isEmpty(dictKey)) {
            return null;
        }
        return dictUtil.getValue(dictCategoryCode, dictKey);
    }
}
