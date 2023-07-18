package com.youceedu.tools.regex;

import com.youceedu.tools.http.ParseJsonToMapUtil;
import org.etsi.uri.x01903.v13.OCSPIdentifierType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: yqsy
 * @ClassName TestPattern
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-06-19 14:36
 * @Version 1.0
 */
public class TestPattern {
    public static void main(String[] args) {
        /*
        //初始化
        String str = "2018-06-13 10:32:23";
        String regex = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";

        //建立正则规范
        Pattern pattern = Pattern.compile(regex);

        //执行规范匹配
        Matcher matcher = pattern.matcher(str);

        if (matcher.matches()){
            System.out.println("str日期合法");
        }else {
            System.out.println("str日期不合法");
        }
         */

        /*//初始化
        String str2 = "   sadsf@163.com    test@123.com  ";
        String regex2 = "(\\w+)@(\\w+)\\.(\\w+)";

        //建立正则规范
        Pattern pattern2 = Pattern.compile(regex2);

        //执行规范-匹配
        Matcher matcher1 = pattern2.matcher(str2);

        //匹配结果
        if (matcher1.matches()){
            System.out.println("matches邮箱合法");
        }else {
            System.out.println("matches邮箱不合法");
        }

//        if (matcher1.find()){
//            System.out.println("find邮箱合法");
//        }else {
//            System.out.println("find邮箱不合法");
//        }

*//*        if (matcher1.find()){
            System.out.println(matcher1.groupCount());
            System.out.println(matcher1.group());
            System.out.println(matcher1.group(1));
            System.out.println(matcher1.group(2));
            System.out.println(matcher1.group(3));
        }*//*

        //后面的也可以打印出来
        while (matcher1.find()){
            System.out.println(matcher1.groupCount());
            System.out.println(matcher1.group());
            System.out.println(matcher1.group(1));
            System.out.println(matcher1.group(2));
            System.out.println(matcher1.group(3));
        }*/

        //案例3
        String str3 = "book=1&m=${_Randrom(1,2)}";
        String regex3 = "\\$\\{_(\\w+)(\\([\\w,]*\\))\\}";

        Pattern pattern = Pattern.compile(regex3);
        Matcher matcher = pattern.matcher(str3);

        while (matcher.find()){
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(2).replace("(","").replace(")",""));
        }

        //从第一个字符进行匹配
//        if (matcher.lookingAt()){
//            System.out.println("合法");
//        }else {
//            System.out.println("不合法");
//        }


    }
}
