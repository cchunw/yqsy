package com.youceedu.interf.functions;

import java.util.UUID;

/**
 * @program: yqsy
 * @ClassName UUIDFunction
 * @Description 随机生成字符串方法类
 * @Author chen chunwei
 * @create: 2023-07-10 14:07
 * @Version 1.0
 */
public class UUIDFunction implements Function{
    @Override
    public String execute(String[] args) {
        //初始化
        String result = null;

        if (args.length ==0){
            //生成一个唯一的UUID字符串
            result = UUID.randomUUID().toString().replace("-", "");
        }

        return result;
    }

    @Override
    public String getReferenceKey() {
        return "UUID";
    }
}
