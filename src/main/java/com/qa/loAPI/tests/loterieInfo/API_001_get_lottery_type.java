package com.qa.loAPI.tests.loterieInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.API_F02.util.TestUtil_Json;
import com.qa.loAPI.base.TestBase;
import com.qa.loAPI.restclient.RestClient_gPPD;
import com.qa.loAPI.util.TestUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;


/**
 * @author urPaPa
 * @date 2020/9/27 17:06
 */
public class API_001_get_lottery_type extends TestBase {

    TestBase testBase;
    RestClient_gPPD restClient;
    CloseableHttpResponse closeableHttpResponse;
    //host
    private String host;
    //Excel路径
    String url;


    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        //接口endpoint
        host = prop.getProperty("Host");
        url = host + "lottery_info/get_lottery_type";
    }

    @Test
    public void get_lottery_type() throws Exception {
        restClient = new RestClient_gPPD();
        //header
        HashMap<String ,String> postHeader = new HashMap<String, String>();
        postHeader.put("Content-Type","application/json");

        //发送登录请求
        closeableHttpResponse = restClient.postGet(url,postHeader);
        System.out.println("closeableHttpResponse is:" + closeableHttpResponse);
        int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode,200);

        //断言响应json内容中name和job是不是期待结果
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        JSONObject responseJson = JSON.parseObject(responseString);
        System.out.println("responseJsonData is:" + responseJson);

        String typeName = TestUtil_Json.getValueByJPath(responseJson, "data[0]/typeName");//$.data[*].typeName
        System.out.println("data[0]/typeName is:" + typeName);
        String typeCode = TestUtil_Json.getValueByJPath(responseJson, "data[0]/typeCode");
        System.out.println("data[0]/typeCode is:" + typeCode);
        Assert.assertEquals(typeName, "赛车","name is not same");
        Assert.assertEquals(typeCode, "PK10","job is not same");

    }

}
