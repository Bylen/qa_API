package com.qa.API_F02.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSONObject;
import com.qa.API_F02.base.TestBase_CG;
import com.qa.API_F02.restclient.RestClient_getPostPutDelete_CG;
import com.qa.API_F02.util.TestUtil_Json;
/**
 * @author urPaPa
 * @date 2020/9/16 10:48
 */
public class GetAPITest_01_CG02_CG extends TestBase_CG{
    TestBase_CG testBase;
    String host;
    String url;
    RestClient_getPostPutDelete_CG restClient;
    CloseableHttpResponse closeableHttpResponse;

    final static Logger Log = Logger.getLogger(GetAPITest_01_CG02_CG.class);

    @BeforeClass
    public void setUp() {

        testBase = new TestBase_CG();
        Log.info("测试服务器地址为："+ host.toString());
        host = prop.getProperty("HOST");
        Log.info("当前测试接口的完整地址为："+url.toString());
        url = host + "/api/users?page=2";

    }

    @Test
    public void getAPITest() throws ClientProtocolException, IOException {
        Log.info("开始执行用例...");
        restClient = new RestClient_getPostPutDelete_CG();
        closeableHttpResponse = restClient.get(url);

        //断言状态码是不是200
        Log.info("测试响应状态码是否是200");
        int statusCode = restClient.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "response status code is not 200");

        JSONObject responseJson = restClient.getResponseJson(closeableHttpResponse);
        //System.out.println("respon json from API-->" + responseJson);

        //json内容解析
        String s = TestUtil_Json.getValueByJPath(responseJson,"data[0]/first_name");
        System.out.println(s);
        Log.info("执行JSON解析，解析的内容是 " + s);
        //System.out.println(s);
        Log.info("接口内容响应断言");
        Assert.assertEquals(s, "Michael","first name is not Michael");
        Log.info("用例执行结束...");
    }

}
