package com.youceedu.interf.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerImpl implements IRetryAnalyzer{
	/**
	 * 原版本方法是  Logger.getLogger(RetryAnalyzerImpl.class);
	 */
	private static Logger logger = LogManager.getLogger(RetryAnalyzerImpl.class);
	
	/**
	 * retry初始值,最大值
	 */
	private int retryCount = 1;
	private int retryMaxCount = 3;
	
	@Override
	public boolean retry(ITestResult result){
		if(retryCount < retryMaxCount){
			logger.info("接口第" + retryCount + " 次重试");
			retryCount++;
			return true;
		}
		return false;
	}

}
