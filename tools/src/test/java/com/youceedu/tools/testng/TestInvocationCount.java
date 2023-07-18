package com.youceedu.tools.testng;

import org.testng.annotations.Test;

public class TestInvocationCount {
    @Test(invocationCount = 10000, invocationTimeOut = 10, threadPoolSize = 4, successPercentage = 100)
    public void f() {
        System.out.println("1");
    }


}
