package com.youceedu.interf.util;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.lang.Class;
import com.youceedu.interf.functions.Function;
/**
 * @Title:
 * @Description: 查找类工具类
 * @param: * @param null:
 * @return: * @return: null
 * @author chen chunwei
 * @date 2023/7/18 15:42
 */
public class ClassFinderUtil{
	/**
	 * 初始化
	 */
	private static List<Class<?>> classes = new ArrayList<Class<?>>();
	
	/**
	 * @Title: getAllAssigendClass   
	 * @Description: 获取某类的所有子类
	 * @param: @param cls
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: List<Class<?>>      
	 * @throws
	 */
	public static List<Class<?>> getAllAssigendClass(Class<?> cls) throws Exception {
		//初始化
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (Class<?> c : getClasses(cls)) {
			if(cls.isAssignableFrom(c) && !cls.equals(c)) {
				classes.add(c);
			}
		}
		return classes;
	}
	
	/**
	 * @Title: getClasses
	 * @Description: 获取某个包下所有的类
	 * @param: @param f
	 * @param: @param pk
	 * @param: @return
	 * @param: @throws Exception
	 * @return: List<Class<?>>
	 * @throws
	 */
	public static List<Class<?>> getClasses(File f,String pk) throws Exception {

		for(File file:f.listFiles()) {
			//判断name是文件还是目录
			if(file.isDirectory()) {
				getClasses(file,pk + "." + file.getName());
			}
			
			String name = file.getName();
			if(name.endsWith(".class")) {
				String className = pk + "." + name.substring(0, name.length()-6);
				classes.add(Class.forName(className));
			}
		}
		return classes;
	}
	
	/**
	 * @Title: getClasses   
	 * @Description: 获取某个包下所有的类
	 * @param: @param cls
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: List<Class<?>>      
	 * @throws
	 */
	public static List<Class<?>> getClasses(Class<?> cls) throws Exception {
		//初始化
		List<Class<?>> classes= new ArrayList<Class<?>>();
		
		//得到指定类的部分路径
		String pk = cls.getPackage().getName();
		String path = pk.replace('.', '/');
		
		//通过包路径得到类的绝对路径
		String dirPath = cls.getClassLoader().getResource(path).getPath();
		
		//遍历dirPath得到所有的类存储到classes
		classes = getClasses(new File(dirPath),pk);
		
		return classes;
	}

	public static void main(String[] args) throws Exception {
		List<Class<?>> list = getAllAssigendClass(Function.class);
		for (Class cls:list){
			System.out.println(cls.getName());
		}
	}
}
