package com.qa.API_F02.tests;

import java.io.IOException;

import com.qa.API_F02.base.TestBase;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.API_F02.restclient.RestClient_get_01;
/**
 * @author urPaPa
 * @date 2020/9/13 11:36
 */
public class GetAPITest_01 extends com.qa.API_F02.base.TestBase {
    TestBase testBase;
    String host;
    String url;
    RestClient_get_01 restClient;


    @BeforeClass
    public void setUp() {
        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "api/users";

    }

    @Test
    public void getAPITest() throws ClientProtocolException, IOException {
        restClient = new RestClient_get_01();
        restClient.get(url);
    }

}
