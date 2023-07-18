package com.youceedu.interf.util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.youceedu.interf.functions.Function;

public class FunctionUtil {
	
	/**
	 * 初始化functionsMap
	 */
	private static final Map<String, Class<? extends Function>> functionsMap = new HashMap<String, Class<? extends Function>>();
	
	static{
		try {
			List<Class<?>> clazzes = ClassFinderUtil.getAllAssigendClass(Function.class);
			for(Class<?> clazz:clazzes){
				//对类进行实例化,并进行上传
				Function tempFunc = (Function) clazz.newInstance();
				String referenceKey = tempFunc.getReferenceKey();
				
				if (referenceKey.length() > 0) {
					functionsMap.put(referenceKey, tempFunc.getClass());
				}//{Random RandomDecimalFunction}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static boolean isFunction(String functionName){
		return functionsMap.containsKey(functionName);
	}
	
 	public static String getValue(String functionName,String[] args){
		try{
			return functionsMap.get(functionName).newInstance().execute(args);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

}
