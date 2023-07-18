package com.youceedu.tools.md5;

import com.sun.org.glassfish.gmbal.Description;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @program: yqsy
 * @ClassName TestMd5
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-07-10 13:30
 * @Version 1.0
 */
public class TestMd5 {
    public static void main(String[] args) throws Exception {

        String str = "123456";
        String aMd5 = DigestUtils.md5Hex(str.getBytes());
        System.out.println("str = " + str + " md5=" + aMd5);
        //str = 123456 md5=e10adc3949ba59abbe56e057f20f883e

        //对文件加密
        String file = "D:\\text.txt";
        InputStream input = new FileInputStream(file);
        String fileMd5 = DigestUtils.md5Hex(input);
        System.out.println("file = " + file + " fileMd5=" + fileMd5);
        //file = D:\text.txt fileMd5=e10adc3949ba59abbe56e057f20f883e

        //对字符串加密
        String a = "123456";
        String strMd5 = DigestUtils.md5Hex(a);
        System.out.println("a = " + a + " strMd5=" + strMd5);
        //a = 123456 strMd5=e10adc3949ba59abbe56e057f20f883e

        /*
         * Md5加密，文件内容相同，加密结果就相同
         */


    }
}
