package com.jingyuu.ershoujing.common.utils;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import static com.jingyuu.ershoujing.common.utils.CommonUtil.isEmpty;
import static org.apache.commons.lang3.time.DateUtils.*;

/**
 * 日期工具类
 */
@Slf4j
public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 注意格里历和儒略历交接时的日期差
     */
    private static final transient int gregorianCutoverYear = 1582;

    /**
     * 功能: 将日期对象按照某种格式进行转换，返回转换后的字符串
     *
     * @param date    日期对象
     * @param pattern 转换格式 例：yyyy-MM-dd HH:mm:ss
     */
    public static String DateToString(Date date, String pattern) {
        if (CommonUtil.allIsNotEmpty(date, pattern)) {
            SimpleDateFormat formater = new SimpleDateFormat(pattern);
            return formater.format(date);
        }
        return null;
    }

    /**
     * 功能: 将日期对象按照默认的格式(yyyy-MM-dd HH:mm:ss)格式进行转换，返回转换后的字符串
     *
     * @param date 日期对象
     */
    public static String DateToString(Date date) {
        return DateToString(date, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 功能: 将字符串对象按照某种格式进行转换，返回转换后的日期
     *
     * @param str 字符串
     * @return
     * @throws JyuException
     */
    public static Date StringToDate(String str, String pattern) throws JyuException {
        Date dateTime = null;
        if (CommonUtil.allIsNotEmpty(str, pattern)) {
            SimpleDateFormat formater = new SimpleDateFormat(pattern);
            try {
                dateTime = formater.parse(str);
            } catch (Exception ex) {
                log.warn("String 转换成 Date 参数格式不正确, str:{}, pattern:{}", str, pattern);
                throw new JyuException(ErrorEnum.ARGS_IS_ERROR, "时间转换格式不正确");
            }
        }

        return dateTime;
    }

    /**
     * 功能: 将字符串对象按照默认的格式(yyyy-MM-dd HH:mm:ss)进行转换，返回转换后的日期
     *
     * @param str
     * @return
     * @throws JyuException
     */
    public static Date StringToDate(String str) throws JyuException {
        return StringToDate(str, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 检查传入的参数代表的年份是否为闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        if (year >= gregorianCutoverYear) {
            return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
        }
        return (year % 4 == 0);
    }

    /**
     * 获取今天最后一秒
     *
     * @return
     */
    public static Date getTodayLastTime() {
        return getLastTime(new Date());
    }

    /**
     * 获取今天的开始时间
     *
     * @return
     */
    public static Date getTodayFirstTime() {
        return getFirstTime(new Date());
    }

    /**
     * 获取某一天最后一秒时间
     *
     * @param date
     * @return
     */
    public static Date getLastTime(Date date) {
        if (isEmpty(date)) {
            date = new Date();
        }

        return addSeconds(truncate(addDays(date, 1), Calendar.DAY_OF_MONTH), -1);
    }

    /**
     * 获取指定天的最开始的时间
     *
     * @param date
     * @return
     */
    public static Date getFirstTime(Date date) {
        if (isEmpty(date)) {
            date = new Date();
        }
        return truncate(date, Calendar.DAY_OF_MONTH);
    }


    /**
     * 获取date月的第一天 空时取本月第一天
     *
     * @param date
     * @return
     */
    public static Date getMonthFirstTime(Date date) {
        if (isEmpty(date)) {
            date = new Date();
        }
        return truncate(getFirstTime(date), Calendar.MONTH);
    }

    /**
     * 获取date月的最后一天 空时取本月最后一天
     *
     * @param date
     * @return
     */
    public static Date getMonthLastTime(Date date) {
        if (isEmpty(date)) {
            date = new Date();
        }

        date = addMonths(date, 1);

        return getLastTime(addDays(truncate(date, Calendar.MONTH), -1));
    }

    /**
     * 得到当前时间
     * @return
     */
    public static Date currentDate() {
        return Date.from(Instant.now());
    }

}
