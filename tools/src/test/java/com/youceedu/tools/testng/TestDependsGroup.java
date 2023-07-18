package com.youceedu.tools.testng;

import org.testng.annotations.Test;

/**
 * @program: yqsy
 * @ClassName TestDependsGroup
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-04-30 22:20
 * @Version 1.0
 */
/*公司做业务流接口时使用，比如：登录--购物车--结算--支付--我的订单*/
//执行时候注意：光标放在哪里就会执行哪个方法或者类，执行结果会不同，要放在类上执行全部方法
public class TestDependsGroup {
    @Test(groups = {"init"})
    public void a_serverInitStatus(){
        System.out.println("1");
    }
    @Test(groups = {"init"})
    public void b_serverStarStatus(){
        System.out.println("2");
    }
    @Test(groups = {"init"})
    public void c_serverAfterStatus(){
        System.out.println("3");
    }
    @Test(dependsOnGroups = {"init.*"})
    public void monitor(){
        System.out.println("4");
    }
}