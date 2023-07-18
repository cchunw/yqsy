package com.youceedu.tools.Date;

import java.util.Date;

/**
 * @program: yqsy
 * @ClassName TestDate
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-07-06 21:44
 * @Version 1.0
 */
public class TestDate {
    public static void main(String[] args) {
        //实例化1
        Date date1 = new Date();

        //实例化2
        long tmpDate = 1688651377988L;
        Date date2 = new Date(tmpDate);

        //从1970年0min0s到现在的毫秒数
        long result = date1.getTime();
        System.out.println("从1970年0min0s到现在的毫秒数:" + result);


        boolean before = date1.before(date2);
        System.out.println(before);

        boolean after = date1.after(date2);
        System.out.println(after);

    }
}
