package com.youceedu.interf.functions;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @program: yqsy
 * @ClassName Md5Function
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-07-10 13:50
 * @Version 1.0
 */
public class Md5Function implements Function{
    @Override
    public String execute(String[] args) {
        //初始化
        String result = null;

        if (args.length > 0){
            result = DigestUtils.md5Hex(args[0]);
        }
        return result;
    }

    @Override
    public String getReferenceKey() {
        return "Md5";
    }
}
