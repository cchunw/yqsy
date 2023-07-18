package com.youceedu.tools.Date;

import org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @program: yqsy
 * @ClassName TestDateFormat
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-07-06 22:07
 * @Version 1.0
 */
public class TestDateFormat {
    public static void main(String[] args) {

        System.out.println("--------时间--------");
        DateFormat df = DateFormat.getTimeInstance();
        String format = df.format(new Date());
        System.out.println(format);

        //入参0,1,2,3是限制输出的日期格式
        DateFormat df2 = DateFormat.getTimeInstance(0);
        String format1 = df2.format(new Date());
        System.out.println(format1);

        DateFormat df3 = DateFormat.getTimeInstance(0, Locale.SIMPLIFIED_CHINESE);
        String format2 = df3.format(new Date());
        System.out.println(format2);

        System.out.println("--------日期--------");
        DateFormat dateInstance = DateFormat.getDateInstance();
        String format3 = dateInstance.format(new Date());
        System.out.println(format3);

        DateFormat dateInstance2 = DateFormat.getDateInstance(0);
        String format4 = dateInstance2.format(new Date());
        System.out.println(format4);

        DateFormat dateInstance1 = DateFormat.getDateInstance(0, Locale.SIMPLIFIED_CHINESE);
        String format5 = dateInstance1.format(new Date());
        System.out.println(format5);

        System.out.println("--------日期时间--------");
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance();
        String format6 = dateTimeInstance.format(new Date());
        System.out.println(format6);

        //timeStyle也是0,1,2,3
        DateFormat dateInstance3 = DateFormat.getDateTimeInstance(0,2);
        String format7 = dateInstance3.format(new Date());
        System.out.println(format7);

        DateFormat dateTimeInstance1 = DateFormat.getDateTimeInstance(0, 0, Locale.SIMPLIFIED_CHINESE);
        String format8 = dateTimeInstance1.format(new Date());
        System.out.println(format8);
    }
}
