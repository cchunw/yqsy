package com.youceedu.tools.testng;

import org.testng.annotations.DataProvider;

public class StaticProvider {
    @DataProvider(name = "data1")
    public static Object[][] createData1() {
        return new Object[][]{
                {"zhangsan", 21},
                {"lisi", 22}
        };
    }

    @DataProvider(name = "data2")
    public static Object[][] createData2() {
        return new Object[][]{
                {"hanmeimei", 13},
                {"lilei", 14}
        };
    }

}
