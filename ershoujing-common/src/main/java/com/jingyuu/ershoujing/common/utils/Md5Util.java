package com.jingyuu.ershoujing.common.utils;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

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

//    public static void main(String[] args) {
//        String salt = Md5Util.md5(UUID.randomUUID().toString());
//        System.out.println(salt);
//
//        System.out.println(Md5Util.md5(salt + "123456" + salt));
//    }
}
