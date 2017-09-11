package com.jingyuu.ershoujing.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 身份证工具类
 */
public class IdCardUtil {
    private static int power[] =
            {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    private IdCardUtil() {
    }

    /**
     * 身份证校验
     *
     * @param idcard
     * @return
     */
    public static boolean isValid(String idcard) {
        if (null == idcard || idcard.trim().length() < 15) {
            return false;
        }
        idcard = idcard.trim();
        if (idcard.length() == 15) {
            idcard = convertIdcarBy15bit(idcard);
        }
        if (idcard.length() != 18) {
            return false;
        }
        String idcard17 = idcard.substring(0, 17);
        String idcard18Code = idcard.substring(17, 18);
        char c[] = null;
        String checkCode = "";
        if (isDigital(idcard17)) {
            c = idcard17.toCharArray();
        } else {
            return false;
        }
        if (null != c) {
            int bit[] = new int[idcard17.length()];
            bit = converCharToInt(c);
            int sum17 = 0;
            sum17 = getPowerSum(bit);
            checkCode = getCheckCodeBySum(sum17);
            if (null == checkCode) {
                return false;
            }
            if (!idcard18Code.equalsIgnoreCase(checkCode)) {
                return false;
            }
        }
        return true;
    }

    private static String convertIdcarBy15bit(String idcard) {
        String idcard17 = null;
        if (null == idcard || idcard.length() != 15) {
            return idcard;
        }
        if (isDigital(idcard)) {
            String birthday = idcard.substring(6, 12);
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cday = Calendar.getInstance();
            cday.setTime(birthdate);
            String year = String.valueOf(cday.get(Calendar.YEAR));

            idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

            char c[] = idcard17.toCharArray();
            String checkCode = "";

            if (null != c) {
                int bit[] = new int[idcard17.length()];
                bit = converCharToInt(c);
                int sum17 = 0;
                sum17 = getPowerSum(bit);
                checkCode = getCheckCodeBySum(sum17);
                if (null == checkCode) {
                    return idcard;
                }
                idcard17 += checkCode;
            }
        } else {
            return idcard;
        }
        return idcard17;
    }

    private static boolean isIdcard(String idcard) {
        return idcard == null || "".equals(idcard) ? false : Pattern.matches(
                "(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idcard);
    }

    private static boolean is15Idcard(String idcard) {
        return idcard == null || "".equals(idcard) ? false : Pattern.matches(
                "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$",
                idcard);
    }

    private static boolean is18Idcard(String idcard) {
        return Pattern
                .matches(
                        "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$",
                        idcard);
    }

    private static boolean isDigital(String str) {
        return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
    }

    private static int getPowerSum(int[] bit) {
        int sum = 0;
        if (power.length != bit.length) {
            return sum;
        }
        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    private static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }

    private static int[] converCharToInt(char[] c)
            throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    public static Date getBirthday(String idcard) {
        if (isValid(idcard)) {
            idcard = convertIdcarBy15bit(idcard);
            String birthday = idcard.substring(6, 14);
            try {
                return new SimpleDateFormat("yyyyMMdd").parse(birthday);
            } catch (ParseException ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static boolean isMale(String idcard) {
        if (isValid(idcard)) {
            idcard = convertIdcarBy15bit(idcard);
            String id17 = idcard.substring(16, 17);
            return Integer.parseInt(id17) % 2 != 0;
        } else {
            return true;
        }
    }

}
