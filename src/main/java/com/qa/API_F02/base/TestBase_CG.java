package com.qa.API_F02.base;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author urPaPa
 * @date 2020/9/14 15:51
 */

public class TestBase_CG {
    //这个类作为所有接口请求的父类，加载读取properties文件
    public Properties prop;
    public int RESPNSE_STATUS_CODE_200 = 200;
    public int RESPNSE_STATUS_CODE_201 = 201;
    public int RESPNSE_STATUS_CODE_404 = 404;
    public int RESPNSE_STATUS_CODE_500 = 500;
    //构造函数
    public TestBase_CG(){
        try{
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/API_F02/config/config.properties");
            PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resources/log4j.properties");
            prop.load(fis);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
