package com.youceedu.tools.random;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.glassfish.gmbal.Description;

import java.util.Random;

public class TestRandom {
    public static void main(String[] args) {
        //实例化
        Random random = new Random();
        /*
        //生成一个int整数取值范围的伪随机数
        System.out.println("random.nextInt()= " + random.nextInt());

        //生成一个0-bound之间的伪随机“整数”，bound是上限
        System.out.println("random.nextInt(10)= " + random.nextInt(10));
        //生成[1,11)之前伪随机整数
        System.out.println("random.nextInt(10) + 1= " + (random.nextInt(10) + 1));

        //生成一个long整数范围伪随机“整数”
        System.out.println("random.nextLong()= " + random.nextLong());

        //伪随机生成true和false
        System.out.println("random.nextBoolean()= " + random.nextBoolean());

        //生成[0.0,1.0)之间伪随机float数
        System.out.println("random.nextFloat()= " + random.nextFloat());
        System.out.println("0-5.0之间随机float数 = " + random.nextFloat() * 5);
        System.out.println("0-2.5之间随机float数 = " + (random.nextFloat() * 1.5 + 1));

        //生成[0.0,1.0）之间的伪随机double数
        System.out.println("random.nextDouble()= " + random.nextDouble());
        System.out.println("0-5.0之间随机double数 = " +random.nextDouble()*5);
        System.out.println("0-2.5之间随机double数 = " + (random.nextDouble()*1.5+1));*/

       /* //随机截取呢个字符组成字符串
        String str = "adfahlsdjfhajhdfjajdhWERQWERQWERBAJSDFKAJEBAKJDKAFHGJrjakdsf23048798";
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(str.length());
            //返回指定索引处的字符值
            char c = str.charAt(number);
            sb.append(c);
        }
        System.out.println(sb.toString());*/

        /**
         * Random构造方法对比
         */
        Random r = new Random();
        for (int i = 0; i < 7; i++) {
            System.out.print(r.nextInt(37) + " ");
        }
        //第一次结果 3 27 26 4 2 16 12
        //第二次结果 36 33 26 15 31 10 9
        System.out.println();
        Random r2 = new Random(8);
        for (int i = 0; i < 7; i++) {
            System.out.print(r2.nextInt(37) + " ");
        }
        //第一次结果 25 29 28 3 10 1 24
        //第二次结果 25 29 28 3 10 1 24

        //生成随机数，该方法底层逻辑调用的是Random类中的nextDouble方法
        Math.random();
    }
}
