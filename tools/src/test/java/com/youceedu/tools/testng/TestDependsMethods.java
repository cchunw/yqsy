package com.youceedu.tools.testng;

import org.testng.annotations.Test;

//使用场景：业务流流程和dependsOnGroups+groups
public class TestDependsMethods {
    @Test
    public void a_login(){
        System.out.println("1");
    }
    @Test
    public void b_addShoppingCart(){
        System.out.println("2");
    }
    @Test(dependsOnMethods = {"a_login","b_addShoppingCart"})
    public void addOrder(){
        System.out.println("3");
    }
}
