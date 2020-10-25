package com.qa.API_F02.base;

//import org.apache.log4j.PropertyConfigurator;

import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author urPaPa
 * @date 2020/9/11 10:45
 */
public class TestBase {
    //这个类作为所有接口请求的父类，加载读取properties文件
    public Properties prop;
    //构造函数
    public TestBase(){
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
