package com.qa.API_F02.tests;

import com.alibaba.fastjson.JSON;
import com.qa.API_F02.base.TestBase_CG;
import com.qa.API_F02.parameters.PostParameters;
import com.qa.API_F02.restclient.RestClient_getPostPutDelete_CG;
import com.qa.API_F02.util.TestUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.HashMap;
import static com.qa.API_F02.util.TestUtil.dtt;
/**
 * @author urPaPa
 * @date 2020/9/24 16:17
 */
public class TestCase_002_WithToken extends TestBase_CG{
    TestBase_CG testBase;
    RestClient_getPostPutDelete_CG restClient;
    CloseableHttpResponse closeableHttpResponse;
    //host
    String host;
    //Excel路径
    String testCaseExcel;
    //token路径
    String tokenPath;
    //header
    HashMap<String ,String> postHeader = new HashMap<String, String>();
    //登录token
    HashMap<String,String> loginToken  = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        testBase = new TestBase_CG();
        postHeader.put("Content-Type","application/json");
        restClient = new RestClient_getPostPutDelete_CG();
        //接口endpoint
        host = prop.getProperty("HOST");
        //读取配置文件Excel路径
        testCaseExcel=prop.getProperty("testCase_001_Data");
        //读取配置文件token路径
        tokenPath = prop.getProperty("tokenPathF02");
    }

    @DataProvider(name = "postData")
    public Object[][] post() throws IOException {
        return dtt(testCaseExcel,0);

    }
    @DataProvider(name = "getData")
    public Object[][] get() throws IOException{
        return dtt(testCaseExcel,1);

    }

    @DataProvider(name = "deleteData")
    public Object[][] delete() throws IOException{
        return dtt(testCaseExcel,2);
    }



    @Test(dataProvider = "postData")
    public void login(String loginUrl,String username, String passWord) throws Exception {
        PostParameters loginParameters = new PostParameters(username,passWord);
        String userJsonString = JSON.toJSONString(loginParameters);
        //发送登录请求
        closeableHttpResponse = restClient.post(host+loginUrl,userJsonString,postHeader);
        //获取登录后的token
        loginToken = TestUtil.getToken(closeableHttpResponse,tokenPath);
        System.out.println("loginToken is:" + loginToken);
        int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode,200);

    }

    @Test(dataProvider = "getData",dependsOnMethods = {"login"})
    public void getMothed(String url) throws Exception{
        //将token赋值后发送get请求
        closeableHttpResponse = restClient.get(host+url,loginToken);
        int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode,200);
    }

    @Test(dataProvider = "deleteData",dependsOnMethods = {"getMothed"})
    public void deleteMothed(String url) throws IOException{
        closeableHttpResponse = restClient.delete(url);
        int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode,204);
    }

}
