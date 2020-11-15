package com.qa.testNG.examples.data.data01;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;

import java.util.*;

/**
 * @author urPaPa
 * @date 2020/9/20 10:06
 */
public class DataProviderTest extends DataProviderSet{
    @Test(dataProvider = "dataIteratorProvider")
    public void testArrayDataProvider(String val1,String val2,String val3){
        System.out.printf("%s,%s,%s\n",val1,val2,val3);
    }
/*

    @DataProvider(name = "dataArrayProvider")
    public Object[][] dataArrayProvider(){
        Object[][] datas = new Object[4][3];

        datas[0] = new Object[]{"100","101","111"};
        datas[1] = new Object[]{"200","202","222"};
        datas[2] = new Object[]{"300","303","333"};
        datas[3] = new Object[]{"400","404","444"};

        return datas;
    }

    @DataProvider(name = "dataIteratorProvider")
    public Iterator<Object[]> dataProvider(){
        List<Object[]> datas = new ArrayList<Object[]>();

        datas.add(new Object[]{"1001","101","111"});
        datas.add(new Object[]{"200","202","222"});
        datas.add(new Object[]{"300","303","333"});
        datas.add(new Object[]{"400","404","444"});

        return datas.iterator();
    }
*/

    @Test(dataProvider = "excelDataProvider001",dataProviderClass = DataProviderSet_01.class)
    public void testMapDataProvider(HashMap<?,?> map){
        System.out.printf("%s,%s,%s\n",map.get("val1"),map.get("val2"),map.get("val3"));
    }
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
