package com.qa.loAPI.tests.loterieInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.loAPI.base.TestBase;
import com.qa.loAPI.restclient.Https;
import com.qa.loAPI.util.TestUtil;
import com.qa.loAPI.util.TestUtil_Json;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * @author urPaPa
 * @date 2020/10/7 17:25
 */
public class API_005_get_all_odds_info extends TestBase{
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
        url = host + "lottery_info/get_all_odds_info";
    }

    @Test
    public void get_all_odds_info() throws Exception {
        //header
        HashMap<String, String> postHeader = new HashMap<String, String>();
        postHeader.put("Content-Type", "application/x-www-form-urlencoded");

        //params
        HashMap<String, String> postParams = new HashMap<String, String>();
        postParams.put("lotteryId", "4001");

        //发送form请求
        closeableHttpResponse = Https.postForm(url, postParams, postHeader);//發送返回的是String格式
        System.out.println("closeableHttpResponse is:" + closeableHttpResponse);
        JSONObject responseJson = JSON.parseObject(closeableHttpResponse);//將String響應報文轉換成json格式
        int statusCode = responseJson.getIntValue("code");//直接取出code的value
        Assert.assertEquals(statusCode, 200);

        //断言响应json内容中name和job是不是期待结果
        System.out.println("responseJsonData is:" + responseJson);
        String lotteryId = TestUtil_Json.getValueByJPath(responseJson,"data[0]/lotteryId");//對於“{”格式的數據取數據包裡的value
        System.out.println("lotteryId is:" + lotteryId);
        String odds = TestUtil_Json.getValueByJPath(responseJson,"data[0]/odds");
        System.out.println("odds is:" + odds);
        Assert.assertEquals(lotteryId, "4001", "lotteryId is expected");
        Assert.assertEquals(odds, "2.20", "odds is expected");

    }

}
