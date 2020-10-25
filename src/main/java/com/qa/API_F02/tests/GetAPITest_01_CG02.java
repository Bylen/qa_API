package com.qa.API_F02.tests;

import java.io.IOException;

import com.qa.API_F02.restclient.RestClient_get_01_CG;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.API_F02.base.TestBase_CG;
import com.qa.API_F02.util.TestUtil_Json;
/**
 * @author urPaPa
 * @date 2020/9/15 10:21
 */
public class GetAPITest_01_CG02 extends TestBase_CG{
    TestBase_CG testBase;
    String host;
    String url;
    RestClient_get_01_CG restClient;
    CloseableHttpResponse closeableHttpResponse;


    @BeforeClass
    public void setUp() {
        testBase = new TestBase_CG();
        host = prop.getProperty("HOST");
        url = host + "/api/users?page=2";

    }

    @Test
    public void getAPITest() throws ClientProtocolException, IOException {
        restClient = new RestClient_get_01_CG();
        closeableHttpResponse = restClient.get(url);

        //断言状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "response status code is not 200");

        //把响应内容存储在字符串对象
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        //创建Json对象，把上面字符串序列化成Json对象
        JSONObject responseJson = JSON.parseObject(responseString);
        //System.out.println("respon json from API-->" + responseJson);

        //json内容解析
        String s = TestUtil_Json.getValueByJPath(responseJson,"data[0]/first_name");
        System.out.println(s);
        Assert.assertEquals(s, "Michael","first name is not Michael");
    }

}
