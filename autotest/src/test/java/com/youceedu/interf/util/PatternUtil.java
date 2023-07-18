package com.youceedu.interf.util;

import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: yqsy
 * @ClassName PatternUtil
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-06-25 14:26
 * @Version 1.0
 */
public class PatternUtil {

    /**初始化预期值正则表达式*/
    private static String compareResultRegex = "([\\$\\.\\w]+)=(\\w+)";
    private static String depKeyRegex = "([/\\w]+):([\\$\\.\\w]+)";
    private static String reqDataRegex = "([/\\w]+):([\\$\\.\\w]+)";
    private static String functionRegex = "\\$\\{\\_\\_(\\w+)(\\([\\w,]*\\))\\}";
    private static Map<String,String> map = new HashMap<String,String>();
    //
    public static Matcher getMatcher(String regex, String data) {
        //初始化
        Pattern pattern = null;
        Matcher matcher = null;
        try {
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matcher;
    }

    public static void compareResultToReportng(String expResult, String actResult) {
        Matcher matcher = getMatcher(compareResultRegex, expResult);
        while (matcher.find()) {
            Assert.assertEquals(JSONPath.read(actResult, matcher.group(1).toString()), matcher.group(2));
        }
    }

    public static int compareResultToDb(String expResult, String actResult) {
        //初始化返回值
        int flag = 0;
        ArrayList<Integer> list = new ArrayList<>();

        Matcher matcher = getMatcher(compareResultRegex, expResult);
        while (matcher.find()) {
            int status = JSONPath.read(actResult, matcher.group(1)).toString().equals(matcher.group(2)) ? 1 : 0;
            list.add(status);
        }
        if (!list.contains(0)) {
            flag = 1;
        }
        return flag;
    }

    public static void storeResponseValue(String depKey,String actResult){
        Matcher matcher = getMatcher(depKeyRegex, depKey);
        while (matcher.find()){
            String jsonPath = matcher.group(2);
            String value = JSONPath.read(actResult, jsonPath).toString();
            map.put(matcher.group(),value);
        }

    }
    
    public static String handlerReqDataOfDep(String reqData){
        Matcher matcher = getMatcher(reqDataRegex, reqData);
        while (matcher.find()){
            String value = map.get(matcher.group());
            reqData = StringUtil.replaceStr(reqData, matcher.group(), value);
        }
        return reqData;

    }

    public static String handlerReqDataOfFunc(String reqData){
        Matcher matcher = getMatcher(functionRegex, reqData);
        while (matcher.find()) {
            String funName = matcher.group(1);
            String funParam = matcher.group(2).replace("(", "").replace(")", "");

            //得到函数对应的类处理结果
            String value = null;
            if (FunctionUtil.isFunction(funName)){
                value = FunctionUtil.getValue(funName, funParam.split(","));
            }
            reqData = StringUtil.replaceStr(reqData,matcher.group(),value);
        }
        return reqData;
    }
}

