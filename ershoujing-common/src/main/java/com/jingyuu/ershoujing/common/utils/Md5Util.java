package com.jingyuu.ershoujing.common.utils;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author owen
 * @date 2017-09-08
 */
public class Md5Util {

    private Md5Util() {
    }

    /**
     * 生成MD5摘要
     *
     * @param origin 原串
     * @return
     */
    public static String md5(String origin) {
        return DigestUtils.md5Hex(origin);
    }
}
