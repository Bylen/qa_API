package com.qa.loAPI.restclient;


import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author urPaPa
 * @date 2020/9/29 16:13
 */
public class DataFormat {
    public static void main(String[] args) {
        // 参数
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("lotteryTypeId", "1"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        // 路径
        HttpPost httppost = new HttpPost("http://103.139.241.107:8000/lottery_info/get_lottery_info");
        httppost.setEntity(entity);

        // 请求
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            response = httpClient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            try {
                System.out.println("最终结果: \n" + EntityUtils.toString(httpEntity));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
