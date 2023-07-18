package com.youceedu.interf.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: yqsy
 * @ClassName DateTimeUtil
 * @Description 日期时间工具类
 * @Author chen chunwei
 * @create: 2023-07-06 22:52
 * @Version 1.0
 */
public class DateTimeUtil {
    /**
     * @Title: getTimeImpl
     * @Description: 获取时间戳
     * @return: * @return: long
     * @author chen chunwei
     * @date 2023/7/6 23:01
     */
    public static long getTimeImpl(){
        //return  new Date().getTime();
        return System.currentTimeMillis();
    }
    /**
     * @Title: getTime
     * @Description: 获取时间
     * @return: * @return: java.lang.String
     * @author chen chunwei
     * @date 2023/7/6 23:01
     */
    public static  String getTime(){
        return DateFormat.getTimeInstance().format(new Date());
    }

    /**
     * @Title: main
     * @Description: 获取日期
     * @return: * @return:java.lang.String
     * @author chen chunwei
     * @date 2023/7/6 23:01
     */
    public static String getDate(){
        return DateFormat.getDateInstance().format(new Date());
    }

    /**
     * @Title: getDateTime
     * @Description: 获取日期时间
     * @return: * @return: java.lang.String
     * @author chen chunwei
     * @date 2023/7/6 23:03
     */
    public static String getDateTime(){
        return DateFormat.getDateTimeInstance().format(new Date());
    }

    /**
     * @Title: getPatternDateTime
     * @Description: 获取定制化日期时间
     * @param: * @param pattern:
     * @return: * @return: java.lang.String
     * @author chen chunwei
     * @date 2023/7/6 23:11
     */
    public static String getPatternDateTime(String pattern){
        return new SimpleDateFormat(pattern).format(new Date());
    }


    public static void main(String[] args) {
        System.out.println(getTimeImpl());
        System.out.println(getTime());
        System.out.println(getDate());
        System.out.println(getDateTime());
        System.out.println(getPatternDateTime("yyyy-MM-dd HH:mm:ss:SSS"));
    }
}
