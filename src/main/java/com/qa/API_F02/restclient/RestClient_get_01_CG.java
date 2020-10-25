package com.qa.API_F02.restclient;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @author urPaPa
 * @date 2020/9/14 10:15
 */
public class RestClient_get_01_CG {
    //1. Get 请求方法
    public CloseableHttpResponse get(String ulr) throws IOException {
        //創建一個可關閉的httpclient對象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //創建一個httpget的請求對象
        HttpGet httpget = new HttpGet(ulr);
        //執行請求，相當於postman上點擊發送按鈕，然後賦值給httpresponse對象接受
        CloseableHttpResponse httpResponse = httpclient.execute(httpget);
        return httpResponse;

    }


}
