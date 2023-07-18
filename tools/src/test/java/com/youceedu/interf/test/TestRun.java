package com.youceedu.interf.test;

import com.alibaba.fastjson.JSONPath;
import com.youceedu.tools.http.HttpReqUtil;
import com.youceedu.tools.poi.ExcelUtil;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: yqsy
 * @ClassName TestRun
 * @Description 执行接口测试
 * @Author chen chunwei
 * @create: 2023-04-30 22:12
 * @Version 1.0
 */
public class TestRun {
    String filePathParam = null;

    @Parameters({"filePathParam"})
    @BeforeTest
    public void beforeTest(String filePathParam) {
        this.filePathParam = filePathParam;
    }

    @Test(dataProvider = "dp")
    public void httpReq(String id, String isExec, String testCase, String reqType, String reqHost, String reqInterface, String reqData, String expResult, String isDep, String depKey) throws Exception {
        //初始化
        String actResult = null;
        String reqUrl = reqHost + reqInterface;

        Reporter.log("用例编号：" + id);
        Reporter.log("请求接口：" + reqUrl);
        Reporter.log("请求参数：" + reqData);
        Reporter.log("接口预期值：" + expResult);

        //发送http请求---HttpReqUtil.java ===>服务器返回值
        if ("YES".equals(isExec)) {
            if ("GET".equals(reqType)) {
                actResult = HttpReqUtil.sendGet(reqUrl, reqData);
            } else {
                actResult = HttpReqUtil.sendPost(reqUrl, reqData);
            }
        }

        //得到实际值与预期值对比
        String regex = "([\\$\\.\\w]+)=(\\w+)";

        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(expResult);
        while (matcher.find()) {
            Assert.assertEquals(JSONPath.read(actResult, matcher.group(1).toString()),matcher.group(2));
        }

        //预期结果与实际结果对比
    }

    /*
     * @Title: dp
     * @Description: 数据驱动提供接口数据
     * @param:
     * @return: * @return: java.lang.Object[][]
     * @author chen chunwei
     * @date 2023/5/1 13:40
     */
    @DataProvider
    public Object[][] dp() {
        //初始化
        Object[][] data = null;
        try {
            ExcelUtil excelUtil = new ExcelUtil(this.filePathParam);
            data = excelUtil.getArrayCellValue(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
