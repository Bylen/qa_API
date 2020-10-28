package com.qa.testNG.examples.data.data01;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author urPaPa
 * @date 2020/9/20 10:06
 */
public class DataProviderSet {

    @DataProvider(name = "mapDataProvider001")
    public static Iterator<Object[]> mapDataProvider(){
        List<Object[]> datas = new ArrayList<Object[]>();

        HashMap<String,String> map1 = new HashMap<String, String>();
        map1.put("val1","aaa");map1.put("val2","bbb");map1.put("val3","ccc");
        HashMap<String,String> map2 = new HashMap<String, String>();
        map2.put("val1","200");map2.put("val2","202");map2.put("val3","222");
        HashMap<String,String> map3 = new HashMap<String, String>();
        map3.put("val1","300");map3.put("val2","303");map3.put("val3","333");

        datas.add(new Object[]{map1});
        datas.add(new Object[]{map2});
        datas.add(new Object[]{map3});

        return datas.iterator();
    }

    @DataProvider(name = "excelDataProvider001")
    public static Iterator<Object[]> mapListProvider(){
        List<Object[]> datas = new ArrayList<Object[]>();

        InputStream in = null;

        DataFormatter dataFormat = new DataFormatter();
        try {
//            in = DataProviderSet.class.getClassLoader().getResourceAsStream("resources01/testData.xlsx");
            File directory = new File("");//参数为空
            String courseFile = directory.getCanonicalPath() ;
            System.out.println(courseFile);
            XSSFWorkbook workbook = new XSSFWorkbook(courseFile);

            Sheet sheet = workbook.getSheet("post");

            Row header = sheet.getRow(0);

            Iterator<Row> rows = sheet.rowIterator();
            boolean isFirstRow = true;
            while (rows.hasNext()){
                Row row = rows.next();
                if (isFirstRow){
                    isFirstRow = false;
                    continue;
                }

                HashMap<String,String> rowMap = new HashMap<String, String>();
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext()){
                    Cell cell = cells.next();

                    rowMap.put(dataFormat.formatCellValue(header.getCell(cell.getColumnIndex()))
                            ,dataFormat.formatCellValue(cell));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(in !=null)
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return datas.iterator();
    }

}
