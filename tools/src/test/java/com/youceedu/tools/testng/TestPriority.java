package com.youceedu.tools.testng;
import org.testng.annotations.Test;

//优先级，也可以作为业务流依赖，和groups+dependsOnGroups,dependsOnMethods 一样使用
public class TestPriority {
    @Test(priority = 1)
    public void a_serverInitStatus(){
        System.out.println("aa");
    }
    @Test(priority = 2)
    public void b_serverStarStatus(){
        System.out.println("bb");
    }
    @Test(priority = 3)
    public void c_serverAfterStatus(){
        System.out.println("cc");
    }
    @Test(priority = 4)
    public void monitor(){
        System.out.println("dd");
    }
}
