package com.qa.API_F02.util;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
/**
 * @author urPaPa
 * @date 2020/9/13 10:50
 */
public class TestUtil {
    //获取返回的token ,使用JsonPath获取json路径
    public static HashMap<String,String> getToken(CloseableHttpResponse closeableHttpResponse,String jsonPath) throws Exception{
        HashMap<String,String> responseToken = new HashMap<String, String>();
        String responseString = EntityUtils.toString( closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("responseString is" + responseString);
        ReadContext ctx = JsonPath.parse(responseString);
        String Token = ctx.read(jsonPath); //"$.EFPV3AuthenticationResult.Token"
        if(null == Token||"".equals(Token)){
            new Exception("token不存在");
        }

        responseToken.put("x-ba-token",Token);
        System.out.println(responseToken);
        return responseToken;
    }


    //遍历excel，sheet参数
    public static Object[][] dtt(String filePath,int sheetId) throws IOException{

        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sh = wb.getSheetAt(sheetId);
//        HSSFWorkbook wb = new HSSFWorkbook(fis);
//        HSSFSheet sh = wb.getSheetAt(sheetId);
        int numberrow = sh.getPhysicalNumberOfRows();
        int numbercell = sh.getRow(0).getLastCellNum();

        Object[][] dttData = new Object[numberrow][numbercell];
        for(int i=0;i<numberrow;i++){
            if(null==sh.getRow(i)||"".equals(sh.getRow(i))){
                continue;
            }
            for(int j=0;j<numbercell;j++) {
                if(null==sh.getRow(i).getCell(j)||"".equals(sh.getRow(i).getCell(j))){
                    continue;
                }
                XSSFCell cell = sh.getRow(i).getCell(j);
//                HSSFCell cell = sh.getRow(i).getCell(j);
                cell.setCellType(CellType.STRING);
                dttData[i][j] = cell.getStringCellValue();
            }
        }

        return dttData;
    }

    //获取状态码
    public static int getStatusCode(CloseableHttpResponse closeableHttpResponse){
        int StatusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        return StatusCode;
    }
}