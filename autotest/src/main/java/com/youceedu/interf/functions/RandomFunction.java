package com.youceedu.interf.functions;

import com.youceedu.interf.util.RandomUtil;

/**
 * @program: yqsy
 * @ClassName RandomFunction
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-07-07 23:41
 * @Version 1.0
 */
public class RandomFunction implements Function{

    @Override
    public String execute(String[] args) {
        //初始化返回值
        String result = null;
        if (args[0].equals("1")){
            //随机整数
            Integer min = Integer.valueOf(args[1]);
            Integer max = Integer.valueOf(args[2]);
            result = String.valueOf(RandomUtil.getRandomInt(min, max));
        }else if (args[0].equals("2")){
            //随机boolean
            result = String.valueOf(RandomUtil.getRandomBoolean());
        }else if (args[0].equals("3")){
            //随机float
            Float min = Float.valueOf(args[1]);
            Float max = Float.valueOf(args[2]);
            result = String.valueOf(RandomUtil.getRandomFloat(min, max));
        }else if (args[0].equals("4")){
            //随机double
            Double min = Double.valueOf(args[1]);
            Double max = Double.valueOf(args[2]);
            result = String.valueOf(RandomUtil.getRandomDouble(min, max));
        }else if(args[0].equals("5")){
            //随机字符串
            if (!args[1].equals(null)){
                result = RandomUtil.getRandomString(Integer.valueOf(args[1]));
            }
        }
        return result;
    }

    @Override
    public String getReferenceKey() {
        return "Random";
    }
}
