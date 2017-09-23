package com.jingyuu.ershoujing.service.support;

import com.jingyuu.ershoujing.common.utils.Md5Util;

/**
 * @author owen
 * @date 2017-09-22
 */
public class UserSupport {
    // 昵称默认前缀
    private static final String NICKNAME_DEFAULT_PREFIX = "鲸鱼_";

    /**
     * 私有构造方法
     */
    private UserSupport() {
    }

    /**
     * 生成昵称
     *
     * @param telephone 手机号
     * @return
     */
    public static String generatorNickName(String telephone) {
        return NICKNAME_DEFAULT_PREFIX + telephone;
    }

    /**
     * 生成密码
     *
     * @param password 密码明文
     * @param salt     盐值
     * @return 密码MD5
     */
    public static String generatorPassword(String password, String salt) {
        return Md5Util.md5(salt + password + salt);
    }

    /***
     * 随机生成密码
     *
     * 算法:
     * 取手机号码第6位数字
     * 如果为偶数，则先从手机号码最后一位取，在从手机号码第二位，以此类推
     * 如果为奇数，则先取手机号码第一位，在取手机号码倒数第二位，以此类推
     *
     * 按照以上规则，取得4位数字后，追加手机号码MD5摘要前4位（字母转大写）
     *
     * @param telephone 手机号
     * @return
     */
    public static String generatorPassword(String telephone) {
        int sixNumber = telephone.charAt(5) - 48;
        StringBuffer strBuff = new StringBuffer();
        int index;
        for (int i = 0, j = telephone.length(), k = 4; k > 0; ) {
            // 从左至右
            if (sixNumber % 2 == 0) {
                index = i % 2 == 0 ? j - i - 1 : i;
            } else {
                // 从右至左
                index = i % 2 == 0 ? i : j - i - 1;
            }
            strBuff.append(telephone.charAt(index));
            i++;
            k--;
        }
        String telephoneMd5 = Md5Util.md5(telephone);
        strBuff.append(telephoneMd5.toUpperCase().substring(0, 4));
        return strBuff.toString();
    }
}
