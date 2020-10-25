package com.qa.API_F02.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.API_F02.base.TestBase_CG;
import com.qa.API_F02.data.Users;
import com.qa.API_F02.restclient.RestClient_getPost_01;
import com.qa.API_F02.util.TestUtil_Json;

/**
 * @author urPaPa
 * @date 2020/9/15 10:48
 */
public class PostApiTest_Basic extends TestBase_CG {
    TestBase_CG testBase;
    String host;
    String url;
    RestClient_getPost_01 restClient;
    CloseableHttpResponse closeableHttpResponse;


    @BeforeClass
    public void setUp() {
        testBase = new TestBase_CG();
        host = prop.getProperty("HOST");
        url = host + "/api/users";

    }

    @Test
    public void postApiTest() throws ClientProtocolException, IOException {
        restClient = new RestClient_getPost_01();
        //准备请求头信息
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到

        //对象转换成Json字符串
        Users user = new Users("Anthony","tester");
        String userJsonString = JSON.toJSONString(user);
        //System.out.println(userJsonString);

        closeableHttpResponse = restClient.post(url, userJsonString, headermap);

        //验证状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_201,"status code is not 201");

        //断言响应json内容中name和job是不是期待结果
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        JSONObject responseJson = JSON.parseObject(responseString);
        //System.out.println(responseString);
        String name = TestUtil_Json.getValueByJPath(responseJson, "name");
        String job = TestUtil_Json.getValueByJPath(responseJson, "job");
        Assert.assertEquals(name, "Anthony","name is not same");
        Assert.assertEquals(job, "tester","job is not same");

    }

}
