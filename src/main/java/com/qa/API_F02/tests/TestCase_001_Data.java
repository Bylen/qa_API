package com.qa.API_F02.tests;

import com.alibaba.fastjson.JSON;
import com.qa.API_F02.base.TestBase_CG;
import com.qa.API_F02.parameters.PostParameters;
import com.qa.API_F02.restclient.RestClient_getPostPutDelete_CG;
import com.qa.API_F02.util.TestUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

import static com.qa.API_F02.util.TestUtil.dtt;
/**
 * @author urPaPa
 * @date 2020/9/17 10:30
 */
public class TestCase_001_Data extends TestBase_CG{
    TestBase_CG testBase;
    RestClient_getPostPutDelete_CG restClient;
    CloseableHttpResponse closeableHttpResponse;
    //host根url
    String host;
    //Excel路径
    String testCaseExcel;
    //header
    HashMap<String ,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        testBase = new TestBase_CG();
        restClient = new RestClient_getPostPutDelete_CG();
        postHeader.put("Content-Type","application/json");
        //载入配置文件，接口endpoint
        host = prop.getProperty("HOST");
        //载入配置文件，post接口参数
        testCaseExcel=prop.getProperty("testCase_001_Data");

    }

    @Test(dataProvider = "postData")
    public void login(String loginUrl,String email, String passWord) throws Exception {
        //使用构造函数将传入的用户名密码初始化成登录请求参数
        PostParameters loginParameters = new PostParameters(email,passWord);
        //将登录请求对象序列化成json对象
        String userJsonString = JSON.toJSONString(loginParameters);
        //发送登录请求
        closeableHttpResponse = restClient.post(host+loginUrl,userJsonString,postHeader);
        //从返回结果中获取状态码
        int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode,200);

    }
    @DataProvider(name = "postData")
    public Object[][] post() throws IOException {
        return dtt(testCaseExcel,0);

    }
    @AfterClass
    public void endTest(){
        System.out.print("测试结束");
    }

}
