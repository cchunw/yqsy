package com.youceedu.tools.testng;
import org.testng.annotations.Test;

public class TestForDataProvider {
    @Test(dataProvider = "data2", dataProviderClass = StaticProvider.class)
    public void test1(String name, Integer age) {
        System.out.println(name + "的年纪：" + age + "岁");
    }
}


