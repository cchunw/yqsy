package com.youceedu.interf.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.*;

/**
 * @program: yqsy
 * @ClassName TestListenerImpl
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-07-05 16:20
 * @Version 1.0
 */
public class TestListenerImpl implements ITestListener {
    //step3 存放重复的ITestResult
    List<ITestResult> testsRepeatToBeRemoved = new ArrayList<ITestResult>();
    private static Logger logger = LogManager.getLogger(TestListenerImpl.class);

    @Override
    public void onFinish(ITestContext context) {
        /*1、依据源码先输出：成功or失败用例集合--->可看到每条用例结果格式
        2、对每个ITestResult进行hashcode并存入Set集合（多个重复hashcode，只存储1个）
        3、据2得到的值进行判断，将重复的ITestResult存入list集合（testsRepeatToBeRemoved）
        4、在allFailedTests中删除testsRepeatToBeRemoved中存储的重复ITestResult*/
        //step1 遍历得到成功的用例结果
        Set<Integer> passedTestIds = new HashSet<>();
        Set<ITestResult> allPassTests = context.getPassedTests().getAllResults();
        for (ITestResult passTestResult : allPassTests) {
            int passHashCodeId = getHashCode(passTestResult);
            System.out.println("成功用例结果hashcode -->" + passHashCodeId);
            passedTestIds.add(passHashCodeId);

        }

        //step2 遍历得到失败的用例结果
        Set<Integer> failedTestIds = new HashSet<>();
        Set<ITestResult> allFailedTests = context.getFailedTests().getAllResults();
        for (ITestResult failedTestResult : allFailedTests) {
            int failHashCodeId = getHashCode(failedTestResult);
            System.out.println("失败用例结果hashcode -->" + failHashCodeId);

            //step3 在失败用例结果集中筛选出重复的
            if (failedTestIds.contains(failHashCodeId) || passedTestIds.contains(failHashCodeId)) {
                testsRepeatToBeRemoved.add(failedTestResult);
            } else {
                failedTestIds.add(failHashCodeId);
            }

        }

        System.out.println("failedTestIds Set集合数据长度=" + failedTestIds.size());
        System.out.println("testsRepeatToBeRemoved 集合数据长度 = "+ testsRepeatToBeRemoved.size());

        for (ITestResult repeatITestResult : testsRepeatToBeRemoved){
            allFailedTests.remove(repeatITestResult);
        }
        logger.info("重试结果去重完毕");
    }

    private int getHashCode(ITestResult testResult) {
        int id = testResult.getTestClass().getName().hashCode();
        id = id + testResult.getMethod().getMethodName().hashCode();
        id = id + (testResult.getParameters() != null ? Arrays.hashCode(testResult.getParameters()) : 0);
        return id;
    }
}

