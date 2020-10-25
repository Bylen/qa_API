package com.qa.loAPI.tests.loterieInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qa.loAPI.base.TestBase;
import com.qa.loAPI.restclient.Https;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * @author urPaPa
 * @date 2020/10/7 17:49
 */
public class API_006_get_new_twenty_record extends TestBase{
    TestBase testBase;
    String closeableHttpResponse;
    //host
    private String host;
    String url;

    @BeforeClass
    public void setUp() {
        testBase = new TestBase();
        //接口endpoint
        host = prop.getProperty("Host");
        url = host + "lottery_info/get_new_twenty_record";
    }

    @Test
    public void get_new_twenty_record() throws Exception {
        //header
        HashMap<String, String> postHeader = new HashMap<String, String>();
        postHeader.put("Content-Type", "application/x-www-form-urlencoded");

        //params
        HashMap<String, String> postParams = new HashMap<String, String>();
        postParams.put("userId", "240");
        postParams.put("lotteryId","4001");
        postParams.put("pageSize","6");
        postParams.put("pageNum","0");

        //发送form请求
        closeableHttpResponse = Https.postForm(url, postParams, postHeader);//發送返回的是String格式
        System.out.println("closeableHttpResponse is:" + closeableHttpResponse);
        JSONObject responseJson = JSON.parseObject(closeableHttpResponse);//將String響應報文轉換成json格式
        int statusCode = responseJson.getIntValue("code");//直接取出code的value
        Assert.assertEquals(statusCode, 200);
        Assert.assertTrue(closeableHttpResponse.contains("07,02,03,08,05,06,04,09,01,10"));//特例斷言

        //断言响应json内容中name和job是不是期待结果
        System.out.println("responseJsonData is:" + responseJson);
//        String list = responseJson.getJSONObject("data").getString("list");//對於“[]”格式的數據取數據包裡的value

        /*全體斷言01*/
        JSONArray list = responseJson.getJSONObject("data").getJSONArray("list");
        int temp = 0;
        for (Object obj:list
             ) {
            String expectNo= ((JSONObject) obj).getString("expectNo");//json轉換成String
            if (temp != 0) {
                Assert.assertEquals(Integer.parseInt(expectNo) + 1, temp);//String轉換成int
            }
            temp = Integer.parseInt(expectNo);//String轉換成int
            System.out.println("expectNo is:" + expectNo);
        }

        //响应体，toString方法可以将响应体转换成字符串
//        String entityStr =  EntityUtils.toString(closeableHttpResponse.getEntity());
//        System.out.println(entityStr);

        /*全體斷言02*/
        String temp0 = null;
        for (Object obj:list
        ) {
            String openLotteryNo= ((JSONObject) obj).getString("openLotteryNo");
            if (temp0 != null) {
                Assert.assertTrue(closeableHttpResponse.contains(temp0));

            }
            temp0 = openLotteryNo;
            System.out.println("actual openLotteryNo is:" + openLotteryNo);
        }
        System.out.println("list is:" + list);

    }
}
