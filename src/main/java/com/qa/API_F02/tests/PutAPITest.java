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
import com.qa.API_F02.restclient.RestClient_getPostPutDelete;
import com.qa.API_F02.util.TestUtil_Json;
/**
 * @author urPaPa
 * @date 2020/9/16 10:19
 */
public class PutAPITest extends TestBase_CG{
    TestBase_CG testBase;
    String host;
    String url;
    RestClient_getPostPutDelete restClient;
    CloseableHttpResponse closeableHttpResponse;


    @BeforeClass
    public void setUp() {
        testBase = new TestBase_CG();
        host = prop.getProperty("HOST");
        url = host + "/api/users/2";

    }

    @Test
    public void putTest() throws ClientProtocolException, IOException{
        restClient = new RestClient_getPostPutDelete();
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到

        //对象转换成Json字符串
        Users user = new Users("Anthony","automation tester");
        String userJsonString = JSON.toJSONString(user);
        //System.out.println(userJsonString);

        closeableHttpResponse = restClient.put(url, userJsonString, headermap);

        //验证状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200,"response status code is not 200");

        //验证名称为Anthony的jon是不是 automation tester
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        JSONObject responseJson = JSON.parseObject(responseString);
        String jobString = TestUtil_Json.getValueByJPath(responseJson, "job");
        System.out.println(jobString);
        Assert.assertEquals(jobString, "automation tester","job is not same");
    }

}
