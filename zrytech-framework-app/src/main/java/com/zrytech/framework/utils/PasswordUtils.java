package com.zrytech.framework.utils;

import com.zrytech.framework.base.constant.Constant;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import static com.zrytech.framework.base.constant.Constant.ALGORITHM_NAME;

public class PasswordUtils {
    /**
     * 对业务模块的用户密码加密
     * @param:password
     * @param:userCouont
     * @return:string
     */
    public static String encryptStringPassword(String password,String userCount) {
        String credentialsSalt = userCount + Constant.SALT;
        String hashAlgorithmName = ALGORITHM_NAME;
        Object salt = new Md5Hash(credentialsSalt);
        //Object salts= ByteSource.Util.bytes(userCount + Constant.SALT);
        int hashIterations = 2;
        Object result = new SimpleHash(hashAlgorithmName, password, salt, hashIterations);
        return result.toString();
    }
}
