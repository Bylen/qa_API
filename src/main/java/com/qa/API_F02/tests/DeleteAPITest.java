package com.qa.API_F02.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.API_F02.base.TestBase_CG;
import com.qa.API_F02.restclient.RestClient_getPostPutDelete;
/**
 * @author urPaPa
 * @date 2020/9/16 10:23
 */
public class DeleteAPITest extends TestBase_CG{
    TestBase_CG testBase;
    String host;
    String url;
    RestClient_getPostPutDelete restClient;
    CloseableHttpResponse closeableHttpResponse;


    @BeforeClass
    public void setUp() {
        testBase = new TestBase_CG();
        host = prop.getProperty("HOST");
        url = host + "/api/users/2";  //直接在这个网站可以找到delete的api

    }

    @Test
    public void deleteApiTest() throws ClientProtocolException, IOException {
        restClient = new RestClient_getPostPutDelete();
        closeableHttpResponse = restClient.delete(url);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 204,"status code is not 204");
    }

}
