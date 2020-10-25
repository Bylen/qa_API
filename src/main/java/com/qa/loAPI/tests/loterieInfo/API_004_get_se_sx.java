package com.qa.loAPI.tests.loterieInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.API_F02.util.TestUtil_Json;
import com.qa.loAPI.base.TestBase;
import com.qa.loAPI.restclient.Https;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * @author urPaPa
 * @date 2020/10/7 17:16
 */
public class API_004_get_se_sx extends TestBase {
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
        url = host + "lottery_info/get_se_sx";
    }

    @Test
    public void get_se_sx() throws Exception {
        //header
        HashMap<String, String> postHeader = new HashMap<String, String>();
        postHeader.put("Content-Type", "application/json");

        //发送form请求
        closeableHttpResponse = Https.postForm(url, postHeader);//發送返回的是String格式
        System.out.println("closeableHttpResp is:" + closeableHttpResponse);
        JSONObject responseJson = JSON.parseObject(closeableHttpResponse);//將String響應報文轉換成json格式
        int statusCode = responseJson.getIntValue("code");//直接取出code的value
        Assert.assertEquals(statusCode, 200);

        //断言响应json内容中name和job是不是期待结果
        System.out.println("responseJsonData is:" + responseJson);

        String name0 = TestUtil_Json.getValueByJPath(responseJson, "data[0]/name");//$.data[*].typeName
        System.out.println("data[0]/name is:" + name0);
        String name1 = TestUtil_Json.getValueByJPath(responseJson, "data[1]/name");
        System.out.println("data[1]/name is:" + name1);
        Assert.assertEquals(name0, "鼠", "name0 is not same");
        Assert.assertEquals(name1, "牛", "name1 is not same");

    }

}
