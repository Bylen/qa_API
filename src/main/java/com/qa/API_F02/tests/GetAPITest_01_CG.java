package com.qa.API_F02.tests;

import com.qa.API_F02.base.TestBase_CG;
import java.io.IOException;

import com.qa.API_F02.restclient.RestClient_get_01_CG;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * @author urPaPa
 * @date 2020/9/14 15:59
 */
public class GetAPITest_01_CG extends TestBase_CG {
    TestBase_CG testBase;
    String host;
    String url;
    RestClient_get_01_CG restClient;
    CloseableHttpResponse closeableHttpResponse;


    @BeforeClass
    public void setUp() {
        testBase = new TestBase_CG();
        host = prop.getProperty("HOST");
        url = host + "/api/users";
    }


    @Test
    public void getAPITest() throws IOException {
        restClient = new RestClient_get_01_CG();
        closeableHttpResponse= restClient.get(url);

        //断言状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200, "response status code is not 200");
    }
}
