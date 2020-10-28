package com.qa.API_F03.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONException;

import com.qa.API_F03.restClient.HttpRequester;
import com.qa.API_F03.util.MobileApiTools;
import com.qa.API_F03.util.ExcelUtil;
/**
 * @author urPaPa
 * @date 2020/9/19 10:33
 */
public class LoginAPI {
    private static Logger logger = Logger.getLogger(LoginAPI.class);

    private final String FILE_PATH = "mobileApiCase.xlsx";
    private final String SHEET_NAME = "Login";
    private final int TITLE_LINE_INDEX = 4;

    private final String RESULT_CODE = "ResultCode";
    private final String TEST_RESULT = "TestResult";
    private final String RUNNING_TIME = "RunningTime";
    private final String ACTUAL_RESULT = "ActualResult";
    private final String RUN = "Run";

    // 需要的参数常量
    private final String MAIL = "mail";
    private final String MOBILE = "mobile";
    private final String PASS_WORD = "password";

    public LoginAPI() {
        try {
            logger.info(LoginAPI.class);

            ExcelUtil.getInstance().setFilePath(FILE_PATH);
            ExcelUtil.getInstance().setSheetName(SHEET_NAME);

            logger.info("初始化: " + ExcelUtil.getInstance().getFilePath() + ", " + ExcelUtil.getInstance().getSheetName());

            MobileApiTools.initializeData(TITLE_LINE_INDEX, RUN, "N", 4);
            MobileApiTools.initializeData(TITLE_LINE_INDEX, ACTUAL_RESULT, "",
                    4);
            MobileApiTools.initializeData(TITLE_LINE_INDEX, RESULT_CODE, "", 4);
            MobileApiTools.initializeData(TITLE_LINE_INDEX, TEST_RESULT, "NT",
                    2);
            MobileApiTools
                    .initializeData(TITLE_LINE_INDEX, RUNNING_TIME, "", 4);

            logger.info(ExcelUtil.getInstance().getFilePath() + ", " + ExcelUtil.getInstance().getSheetName() + "初始化完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login() throws JSONException, IOException {
        String url = "";
        String act = "";
        String method = "";
        List<Map<String, String>> data = null;
        boolean flag = false;

        url = ExcelUtil.getInstance().readExcelCell(0, 1);
        act = ExcelUtil.getInstance().readExcelCell(1, 1);
        method = ExcelUtil.getInstance().readExcelCell(2, 1);
        flag = MobileApiTools.isArgEquals(3, 1, TITLE_LINE_INDEX);

        if (url.equals("") || act.equals("") || method.equals("") || !flag) {
            logger.error("请检查 Excel 中 Interface、Act、Method、ArgName 是否设置正确...");
            //System.out
            //        .println("请检查 Excel 中 Interface、Act、Method、ArgName 是否设置正确...");
            System.exit(-1);
        }

        data = ExcelUtil.getInstance().readExcelAllData(4);

        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                Map<String, String> map = data.get(i);
                String mail = map.get(MAIL);
                String mobile = map.get(MOBILE);
                String password = map.get(PASS_WORD);
                String state = map.get("State");
                String expectedResult = map.get("ExpectedResult");
                String loginName;

                // 如果 state == 0 则用 邮箱登录, 否则使用手机号码登录
                if (Integer.parseInt(state) == 0)
                    loginName = mail;
                else
                    loginName = mobile;

                String param = "Act=" + act + "&" + "LoginName=" + loginName
                        + "&" + "Pwd=" + password;

                Map<String, String> result = HttpRequester.sendPost(method,
                        url, param);
                String code = result.get("code");
                String rsTmp = result.get("result");

                // 将字符串转换为 JSON
                JSONObject object = new JSONObject(Boolean.parseBoolean(rsTmp));
                String actualResult = object.getString("msg");

                String testResult = MobileApiTools.assertResult(expectedResult,
                        actualResult);

                // 写入 Run 列, 执行纪录
                MobileApiTools.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX
                        + 1 + i, RUN, "Y");

                // 写入 http code
                MobileApiTools.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX
                        + 1 + i, RESULT_CODE, code);

                // 设置单元格颜色
                if (Integer.parseInt(code) == 200)
                    ExcelUtil.getInstance().setCellBackgroundColor(
                            TITLE_LINE_INDEX, RESULT_CODE,
                            TITLE_LINE_INDEX + 1 + i, 1);
                else
                    ExcelUtil.getInstance().setCellBackgroundColor(
                            TITLE_LINE_INDEX, RESULT_CODE,
                            TITLE_LINE_INDEX + 1 + i, 1);

                // 写入实际结果
                MobileApiTools.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX
                        + 1 + i, ACTUAL_RESULT, actualResult);

                // 写入测试通过与否
                MobileApiTools.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX
                        + 1 + i, TEST_RESULT, testResult);

                if (testResult.equals("OK"))
                    ExcelUtil.getInstance().setCellBackgroundColor(
                            TITLE_LINE_INDEX, TEST_RESULT,
                            TITLE_LINE_INDEX + 1 + i, 1);
                else
                    ExcelUtil.getInstance().setCellBackgroundColor(
                            TITLE_LINE_INDEX, TEST_RESULT,
                            TITLE_LINE_INDEX + 1 + i, 0);

                // 写入执行时间
                MobileApiTools.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX
                        + 1 + i, RUNNING_TIME, MobileApiTools.getDate());

                logger.info("CaseID: " + map.get("CaseID") + ", CaseName: " + map.get("CaseName") + ", ExpectedResult: " +
                        map.get("ExpectedResult") + ", ActualResult: " + actualResult + ", ResultCode: " + code +
                        ", TestResult: " + testResult);
            }
        }

    }

    public static void main(String[] args) throws JSONException, IOException {
        LoginAPI loginAPI = new LoginAPI();
        loginAPI.login();

    }
}
