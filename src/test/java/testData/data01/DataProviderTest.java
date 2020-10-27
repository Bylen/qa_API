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

    @Test(dataProvider = "mapDataProvider1")
    public void testMapDataProvider(HashMap<?,?> map){
        System.out.printf("%s,%s,%s\n",map.get("val1"),map.get("val2"),map.get("val3"));
    }
/*
    @Test(dataProvider = "mapDataProvider2",dataProviderClass = DataProviderSet.class)
    public void mapDataProviderFromExcel(HashMap<String,String> map){

//        Set<Entry<String,String>> sets = map.entrySet();
//        Iterator<Entry<String,String>> iterEntry = sets.iterator();
//
//        while (iterEntry.hasNext()){
//            Entry<String,String> entry = iterEntry.next();
//            System.out.println(entry.getKey() + "->" + entry.getValue());
//        }
    }

//    @DataProvider(name = "mapDataProvider")
//    public Iterator<Object[]> mapDataProvider(){
//        List<Object[]> datas = new ArrayList<Object[]>();
//
//        HashMap<String,String> map1 = new HashMap<String, String>();
//        map1.put("val1","1001");map1.put("val2","101");map1.put("val3","111");
//        HashMap<String,String> map2 = new HashMap<String, String>();
//        map2.put("val2","200");map2.put("val2","202");map2.put("val3","222");
//        HashMap<String,String> map3 = new HashMap<String, String>();
//        map3.put("val1","300");map3.put("val2","303");map3.put("val3","333");
//
//        datas.add(new Object[]{map1});
//        datas.add(new Object[]{map2});
//        datas.add(new Object[]{map3});
//
//        return datas.iterator();
//    }*/
}
