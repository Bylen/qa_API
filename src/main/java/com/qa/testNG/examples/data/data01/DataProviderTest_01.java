package com.qa.testNG.examples.data.data01;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author urPaPa
 * @date 2020/11/13 16:22
 */
public class DataProviderTest_01 {

    @Test(dataProvider = "excelDataProvider001",dataProviderClass = DataProviderSet_01.class)
    public void mapDataProviderFromExcel(HashMap<String,String> map){

        Set<Map.Entry<String,String>> sets = map.entrySet();
        Iterator<Map.Entry<String,String>> iterEntry = sets.iterator();

        while (iterEntry.hasNext()){
            Map.Entry<String,String> entry = iterEntry.next();
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
    }
}
