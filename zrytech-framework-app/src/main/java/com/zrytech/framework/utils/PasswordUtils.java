package com.zrytech.framework.utils;

import com.zrytech.framework.base.constant.Constant;
import com.zrytech.framework.base.entity.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import static com.zrytech.framework.base.constant.Constant.ALGORITHM_NAME;

public class PasswordUtils {
    /**
     * 对业务模块的用户密码加密
     *
     * @param:password
     * @param:userCouont
     * @return:string
     */
    public static String encryptStringPassword(String password, String userCount) {
        String credentialsSalt = userCount + Constant.SALT;
        String hashAlgorithmName = ALGORITHM_NAME;
        Object salt = new Md5Hash(credentialsSalt);
        //Object salts= ByteSource.Util.bytes(userCount + Constant.SALT);
        int hashIterations = 2;
        Object result = new SimpleHash(hashAlgorithmName, password, salt, hashIterations);
        return result.toString();
    }

    public static String encryptShiroPassword(String userAccount, String password) {
        String credentialsSalt = userAccount + Constant.SALT;

        String hashAlgorithmName = ALGORITHM_NAME;
        String credentials = password;
        Object salt = new Md5Hash(credentialsSalt);
        int hashIterations = 2;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);

//        String newPassword = new SimpleHash(
//                ALGORITHM_NAME,
//                user.getPassword(),
//                ByteSource.Util.bytes(credentialsSalt),
//                HASH_ITERATIONS).toHex();
        return result.toString();
    }
}
