package com.youceedu.interf.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpReqUtil {

    private static CookieStore cookieStore = new BasicCookieStore();
    public static void httpReqConfig(HttpRequestBase httpRequestBase, String param){

        //header配置
        httpRequestBase.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/113.0");
        if (new ParseJsonToMapUtil().isJsonString(param)){
            httpRequestBase.setHeader("Content-Type","application/json;charset=utf-8");
        }else {
            httpRequestBase.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        }

        //超时设置，2s没有相应则中断请求
        RequestConfig Config = RequestConfig.custom()
                        .setConnectionRequestTimeout(2000)
                                .build();
        httpRequestBase.setConfig(Config);

    }

    /**
     * @param param:接口参数
     * @Title: sendGet
     * @Description: java发送http get请求
     * @param: * @param url: 接口地址
     * @return: * @return: java.lang.String
     * @author chen chunwei
     * @date 2023/5/12 16:13
     */
    public static String sendGet(String url, String param) throws Exception {
        //初始化
        String result = null;
        CloseableHttpResponse response = null;
        String finalUrl = url + "?" + param;

        try {
            /*
            HttpClientBuilder custom = HttpClients.custom();
            HttpClientBuilder httpClientBuilder = custom.setDefaultCookieStore(cookieStore);
            CloseableHttpClient httpclient = custom.build();
             */

            //实例化得到httpclient对象
            //CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();
            HttpGet httpGet = new HttpGet(finalUrl);

            //增加请求配置
            httpReqConfig(httpGet,param);

            //发送get请求并得到服务器返回值
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");
            }
        } finally {
            response.close();
        }
        return result;
    }
    /**
     * @Title: sendPost
     * @Description: java发送http post请求
     * @param: * @param url: 接口地址
     * @param param: 接口参数
     * @return: * @return: java.lang.String
     * @author chen chunwei
     * @date 2023/5/12 16:22
     */
    public static String sendPost(String url, String param) throws Exception {
        //初始化
        String result = null;
        CloseableHttpResponse response = null;
        String finalUrl = url + "?" + param;

        try {
            //实例化得到httpclient对象
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();
            HttpPost httpPost = new HttpPost(finalUrl);

            //给httpPost对象分配entity
            httpPost.setEntity(new StringEntity(param, "utf-8"));

            //增加请求配置
            httpReqConfig(httpPost,param);

            //发送post请求，并得到服务器返回值
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");
            }
        } finally {
            response.close();
        }
        return result;
    }

    public static void main(String[] args) throws IOException {

    }
}
