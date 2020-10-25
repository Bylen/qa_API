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
 * @date 2020/9/30 10:55
 */
public class API_001_Get_lottery_type_01 extends TestBase {
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
        url = host + "lottery_info/get_lottery_type";
    }

    @Test
    public void get_lottery_type() throws Exception {
        //header
        HashMap<String ,String> postHeader = new HashMap<String, String>();
        postHeader.put("Content-Type","application/json");

        //发送form请求
        closeableHttpResponse = Https.postForm(url,postHeader);//發送返回的是String格式
        System.out.println("closeableHttpResp is:" + closeableHttpResponse);
        JSONObject responseJson = JSON.parseObject(closeableHttpResponse);//將String響應報文轉換成json格式
        int statusCode = responseJson.getIntValue("code");//直接取出code的value
        Assert.assertEquals(statusCode,200);

        //断言响应json内容中name和job是不是期待结果

//        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());//通過EntityUtils獲取格式為String的響應報文
//        JSONObject responseJson = JSON.parseObject(responseString);//將響應報文String格式轉換為json格式
        System.out.println("responseJsonData is:" + responseJson);

        String typeName = TestUtil_Json.getValueByJPath(responseJson, "data[0]/typeName");//$.data[*].typeName
        System.out.println("data[0]/typeName is:" + typeName);
        String typeCode = TestUtil_Json.getValueByJPath(responseJson, "data[0]/typeCode");
        System.out.println("data[0]/typeCode is:" + typeCode);
        Assert.assertEquals(typeName, "赛车","name is not same");
        Assert.assertEquals(typeCode, "PK10","job is not same");

    }
}
