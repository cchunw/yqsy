package com.youceedu.interf.listener;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @program: yqsy
 * @ClassName AnnotationTransformerImpl
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-07-04 16:42
 * @Version 1.0
 */
public class AnnotationTransformerImpl implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        /**
         * 原始版本是这种 annotation.getRetryAnalyzer();
         * 失败用例自动retry
         */
        Class<? extends IRetryAnalyzer> retry = annotation.getRetryAnalyzerClass();
        if (retry == null){
            annotation.setRetryAnalyzer(RetryAnalyzerImpl.class);
        }

    }
}
