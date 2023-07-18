package com.youceedu.tools.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @program: yqsy
 * @ClassName TestSimpleDateFormat
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-07-06 22:38
 * @Version 1.0
 */
public class TestSimpleDateFormat {
    public static void main(String[] args) throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        String format = sdf.format(new Date());
//        System.out.println(format);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.CHINA);

        String format = sdf.format(new Date());
        System.out.println(format);

        Date parse = sdf.parse("2023-07-06 22:44:59:036");
        String format1 = sdf.format(new Date());
        System.out.println(format1);
    }
}
