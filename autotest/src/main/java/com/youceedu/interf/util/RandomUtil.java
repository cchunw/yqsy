package com.youceedu.interf.util;

import java.util.Random;

/**
 * @program: yqsy
 * @ClassName RandomUtil
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-07-07 23:07
 * @Version 1.0
 */
public class RandomUtil {
    //初始化
    private static Random random = new Random();
    private static String str = "werqwerhadfawerTYIVHJHFGHGHgjh1231343HGH134afajdshfjahdfj";

    /**
     * @param max:
     * @Title: getRandomInt
     * @Description: 得到[min，max+1)或[min,max)之间的随机整数
     * @param: * @param min:
     * @return: * @return: int
     * @author chen chunwei
     * @date 2023/7/7 23:14
     */
    public static int getRandomInt(int min, int max) {
        return random.nextInt(max) + min;
    }

    /**
     * @Title: getRandomLong
     * @Description: 得到long范围的随机整数
     * @param:
     * @return: * @return: long
     * @author chen chunwei
     * @date 2023/7/7 23:18
     */
    public static long getRandomLong() {
        return random.nextLong();
    }

    /**
     * @Title: getRandomBoolean
     * @Description: 得到伪随机数true或false
     * @param:
     * @return: * @return: boolean
     * @author chen chunwei
     * @date 2023/7/7 23:19
     */
    public static boolean getRandomBoolean() {
        return random.nextBoolean();
    }

    /**
     * @Title: getRandomFloat
     * @Description: 得到[min, max)或[min,max+min)范围内的伪随机数
     * @param:
     * @return: * @return: float
     * @author chen chunwei
     * @date 2023/7/7 23:21
     */
    public static float getRandomFloat(float min, float max) {
        return random.nextFloat() * max + min;
    }

    /**
     * @Title: getRandomDouble
     * @Description: 得到[min, max)或[min,max+min)范围内的伪随机数
     * @param: * @param min:
     * @param max:
     * @return: * @return: double
     * @author chen chunwei
     * @date 2023/7/7 23:26
     */
    public static double getRandomDouble(double min, double max) {
        return random.nextDouble() * max + min;
    }

    /**
     * @Title: getRandomString
     * @Description: 随机生成指定长度字符串
     * @param: * @param length:
     * @return: * @return: java.lang.String
     * @author chen chunwei
     * @date 2023/7/7 23:32
     */
    public static String getRandomString(int length){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            char c = str.charAt(number);
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(getRandomInt(1, 10));
        System.out.println(getRandomLong());
        System.out.println(getRandomBoolean());
        System.out.println(getRandomFloat(1f, 5f));
        System.out.println(getRandomDouble(1d,5d));
        System.out.println(getRandomString(10));
    }
}
