package com.jingyuu.ershoujing.common.utils;

import java.util.Random;

/**
 * @author owen
 * @date 2017-09-20
 */
public class RandomUtil {
//    public static void main(String[] args) {
//        //随机生成纯数字
//        for (int i = 0; i < 15; i++) {
//            System.out.println(createRandomNumber(4));
//        }
//
//        System.out.println("---------------");
//
//        // 生成数字字母
//        for (int i = 0; i < 15; i++) {
//            System.out.println(createRandomNumberAndChar(6));
//        }
//    }

    /***
     * 随机生成指定位数的字符串（字母+数字）
     *
     * 0~9的ASCII为48~57
     * A~Z的ASCII为65~90
     * a~z的ASCII为97~122
     *
     * @param length
     * @return
     */
    public static String createRandomNumberAndChar(int length) {
        StringBuilder strBuff = new StringBuilder();
        Random randIndex = new Random();//随机用以下三个随机生成器
        Random randData = new Random();
        int data = 0;
        for (int i = 0; i < length; i++) {
            int index = randIndex.nextInt(3);
            //目的是随机选择生成数字，大小写字母
            switch (index) {
                case 0:
                    data = randData.nextInt(10);//仅仅会生成0~9
                    strBuff.append(data);
                    break;
                case 1:
                    data = randData.nextInt(26) + 65;//保证只会产生65~90之间的整数
                    strBuff.append((char) data);
                    break;
                case 2:
                    data = randData.nextInt(26) + 97;//保证只会产生97~122之间的整数
                    strBuff.append((char) data);
                    break;
            }
        }
        return strBuff.toString();
    }


    /**
     * 随机生成指定位数的数字
     *
     * @param length
     */
    public static String createRandomNumber(int length) {
        StringBuilder strBuff = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            strBuff.append(rand.nextInt(10));
        }

        return strBuff.toString();
    }
}
