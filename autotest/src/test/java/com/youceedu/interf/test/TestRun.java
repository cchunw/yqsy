package com.youceedu.interf.test;

import com.youceedu.interf.listener.RetryAnalyzerImpl;
import com.youceedu.interf.model.AutoLog;
import com.youceedu.interf.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: yqsy
 * @ClassName TestRun
 * @Description 执行接口测试
 * @Author chen chunwei
 * @create: 2023-04-30 22:12
 * @Version 1.0
 */
public class TestRun {
    /**
     * 初始化
     */
    String filePathParam = null;
    private static List<AutoLog> list = new ArrayList<AutoLog>();
    private static Logger logger = LogManager.getLogger(TestRun.class);

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

        //reqData若有依赖则需从Map中取值
        reqData = PatternUtil.handlerReqDataOfDep(reqData);

        //reqData函数表达式处理
        reqData = PatternUtil.handlerReqDataOfFunc(reqData);

        //打印日志
        Reporter.log("用例编号：" + id);
        Reporter.log("请求接口：" + reqUrl);
        Reporter.log("请求参数：" + reqData);
        Reporter.log("接口预期值：" + expResult);
        logger.info("接口编号：" + id);
        logger.info("请求接口：" + reqUrl);
        logger.info("请求参数：" + reqData);

        //发送http请求---HttpReqUtil.java ===>服务器返回值(实际值)
        if ("YES".equals(isExec)) {
            if ("GET".equals(reqType)) {
                actResult = HttpReqUtil.sendGet(reqUrl, reqData);
            } else {
                actResult = HttpReqUtil.sendPost(reqUrl, reqData);
            }
        } else {
            logger.info("此条接口不执行" + reqUrl);
        }
        //打印实际值
        Reporter.log("接口实际返回值：" + actResult);
        logger.info("接口实际返回值：" + actResult);

        //解决接口被依赖的处理
        if ("YES".equals(isDep)) {
            PatternUtil.storeResponseValue(depKey, actResult);
        }

        //预期值与实际值对比-toDb(写在toReportng上面防止报错不往下执行)
        //测试结果、用例执行时间
        int result = PatternUtil.compareResultToDb(expResult, actResult);

        //建立实体类存储用例运行结果
        list.add(new AutoLog(testCase, reqType, reqUrl, reqData, expResult, actResult, result, DateTimeUtil.getDateTime()));

        //预期值与实际值对比toReportNg
        PatternUtil.compareResultToReportng(expResult, actResult);

    }

    /*
     * @Title: dp
     * @Description: 数据驱动从excel读取数据，提供接口数据
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
            logger.info("TestRun class 数据驱动成功得到数据");
        } catch (Exception e) {
            logger.error(e);
        }
        return data;
    }

    /**
     * @Title: afterTest
     * @Description: 测试结果存储到mysql
     * @param:
     * @return: * @return: void
     * @author chen chunwei
     * @date 2023/6/25 18:41
     */
    @AfterTest
    public void afterTest() throws Exception {
        DbcpUtil.jdbcUpdateBatch(list);
    }
}
