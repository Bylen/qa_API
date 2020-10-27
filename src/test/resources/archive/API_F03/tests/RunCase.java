package com.qa.API_F03.tests;

import java.io.IOException;

import com.alibaba.fastjson.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.API_F03.util.ExcelUtil;
/**
 * @author urPaPa
 * @date 2020/9/19 10:39
 */
public class RunCase {
    @Test
    public void testLogin() throws JSONException, IOException {
        LoginAPI login = new LoginAPI();
        login.login();
        String [] result = ExcelUtil.getInstance().readExcelColumnData(4, "TestResult");
        for (int i = 0; i < result.length; i++) {
            Assert.assertEquals(result[i], "OK");
        }
    }
}
