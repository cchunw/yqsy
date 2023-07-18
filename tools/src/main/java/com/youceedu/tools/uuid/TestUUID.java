package com.youceedu.tools.uuid;

import java.util.UUID;

/**
 * @program: yqsy
 * @ClassName TestUUID
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-07-10 14:02
 * @Version 1.0
 */
public class TestUUID {
    public static void main(String[] args) {
        //System.out.println(UUID.randomUUID().toString().replace("-",""));
        //生成1000个随机字符串
        for (int i = 0; i < 1000; i++) {
            System.out.println(UUID.randomUUID().toString().replace("-",""));
        }

        //图片服务器-->318a6e68fe324ba5b0f7b6257bc88586.jpg
    }
}
