package com.youceedu.tools.classfind;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @program: yqsy
 * @ClassName Test
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-07-01 16:37
 * @Version 1.0
 */
public class Test {
    /**
     * 初始化
     */
    private static List<Class<?>> classes = new ArrayList<>();
    /**
     * @Title: getAllAssigendClass
     * @Description: 获取指定类下所有子类
     * @param: * @param cls:
     * @return: * @return: java.util.List<java.lang.Class<?>>
     * @author chen chunwei
     * @date 2023/7/3 21:52
     */
    public static List<Class<?>> getAllAssigendClass(Class<?> cls) throws Exception {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (Class<?> c : getClasses(cls)) {
            if (cls.isAssignableFrom(c) && !cls.equals(c)) {
                classes.add(c);
            }
        }
        return classes;
    }

    /**
     * @Title:
     * @Description: 获取某个包下所有的类
     * @param: * @param null:
     * @return: * @return: null
     * @author chen chunwei
     * @date 2023/7/3 21:21
     */
    public static List<Class<?>> getClasses(File f, String pk) throws Exception {

        File[] files = f.listFiles();
        for (File file : files) {

            if (file.isDirectory()) {
                String newPath = pk + "." + file.getName();
                //递归，调用本方法
                getClasses(file, newPath);
            }

            String name = file.getName();
            if (name.endsWith(".class")) {
                String className = pk + "." + name.substring(0, name.length() - 6);
                classes.add(Class.forName(className));
            }
        }
        return classes;
    }

    /**
     * @Title: getClasses
     * @Description:获取某个包下所有的类
     * @param: * @param cls:
     * @return: * @return: java.util.List<java.lang.Class<?>>
     * @author chen chunwei
     * @date 2023/7/3 21:22
     */
    public static List<Class<?>> getClasses(Class<?> cls) throws Exception {
        //初始化
        List<Class<?>> classes = new ArrayList<>();

        //step1-A 输出一个类包的名字
        String pk = cls.getPackage().getName();
        String path = pk.replace('.', '/');

        //step1-B 通过包路径，得到类的绝对路径
        String dirPath = cls.getClassLoader().getResource(path).getPath();

        //step2-A 获取绝对路径dirpath下所有的类
        File file = new File(dirPath);
        classes = getClasses(file, pk);

        return classes;
    }

    public static void main(String[] args) throws Exception {
        /*List<Class<?>> tmp = getClasses(Test1.class);
        for (Class cls : tmp) {
            System.out.println(cls.getName());
        }*/

        List<Class<?>> list = getAllAssigendClass(Function.class);
        for (Class cls : list) {
            System.out.println(cls.getName());
        }
    }
}
