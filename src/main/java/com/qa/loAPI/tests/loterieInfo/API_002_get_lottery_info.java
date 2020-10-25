package com.qa.loAPI.tests.loterieInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.loAPI.base.TestBase;
import com.qa.loAPI.parameters.APIP_002_get_lottery_info;
import com.qa.loAPI.restclient.Https;
import com.qa.loAPI.restclient.RestClient_gPPD;
import com.qa.loAPI.util.TestUtil;
import com.qa.loAPI.util.TestUtil_Json;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author urPaPa
 * @date 2020/9/29 15:04
 */
public class API_002_get_lottery_info extends TestBase {
    TestBase testBase;
    String closeableHttpResponse;
    //host
    private String host;
    String url;

    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        //接口endpoint
        host = prop.getProperty("Host");
        url = host + "lottery_info/get_lottery_info";
    }

    @Test
    public void get_lottery_info() throws Exception {
        //header
        HashMap<String ,String> postHeader = new HashMap<String, String>();
        postHeader.put("Content-Type","application/x-www-form-urlencoded");

        //params
        HashMap<String ,String> postParams = new HashMap<String, String>();
        postParams.put("lotteryTypeId","2");

        //发送登录请求
        closeableHttpResponse = Https.postForm(url,postParams,postHeader);//json格式提交數據包；而swagger上要求form表單
        JSONObject responseJson = JSONObject.parseObject(closeableHttpResponse);//通過JSONObject工具將form表單數據格式轉換成json格式
        int statusCode = responseJson.getIntValue("code");
        Assert.assertEquals(statusCode,200);

        //断言响应json内容中name和job是不是期待结果
        System.out.println("responseJsonData is:" + responseJson);

        String lotteryId = TestUtil_Json.getValueByJPath(responseJson, "data[0]/lotteryId");//$.data[*].typeName
        System.out.println("data[0]/lotteryId is:" + lotteryId);
        String lotteryName = TestUtil_Json.getValueByJPath(responseJson, "data[0]/lotteryName");
        System.out.println("data[0]/lotteryName is:" + lotteryName);
        Assert.assertEquals(lotteryId, "4101","lotteryId is not same");
        Assert.assertEquals(lotteryName, "重庆时时彩","lotteryName is not same");

    }

}
