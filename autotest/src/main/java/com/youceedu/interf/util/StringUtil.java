package com.youceedu.interf.util;

/**
 * @Title:
 * @Description: 类替换字符串
 * @param: * @param null:
 * @return: * @return: null
 * @author chen chunwei
 * @date 2023/6/27 20:13
 */
public class StringUtil{
	
	/**
	 * @Title: replaceStr   
	 * @Description: 替换字符串
	 * @param: @param sourceStr
	 * @param: @param matchStr
	 * @param: @param replaceStr
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String replaceStr(String sourceStr,String matchStr,String replaceStr){
		
		//得到matchStr左侧字符串
		int index = sourceStr.indexOf(matchStr);
		//从指定的beginIndex开始，并扩展到索引endIndex-1处的字符
		String beginStr = sourceStr.substring(0,index);
		
		//得到matchStr右侧字符串
		int matLength = matchStr.length();
		int sourLength = sourceStr.length();
		//从指定的beginIndex开始，并扩展到索引endIndex-1处的字符
		String endStr = sourceStr.substring(index+matLength,sourLength);
		
		//重新拼接
		sourceStr = beginStr+replaceStr+endStr;
		System.out.println(sourceStr);
		
		return sourceStr;
	}

	public static void main(String[] args) {
		String sourceStr = "countdown=/public/lazyentrance:$.status&m=${__Random(0,1)}";
		String matchStr ="/public/lazyentrance:$.status";
		String replaceStr = "1";

		replaceStr(sourceStr,matchStr,replaceStr);

	}
	
}
