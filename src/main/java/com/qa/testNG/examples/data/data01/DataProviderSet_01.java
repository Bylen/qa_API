package com.qa.testNG.examples.data.data01;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author urPaPa
 * @date 2020/11/13 15:58
 */
public class DataProviderSet_01 {

    @DataProvider(name = "excelDataProvider001")
    public static Iterator<Object[]> mapListProvider(){
        List<Object[]> datas = new ArrayList<Object[]>();

        InputStream in = null;

        DataFormatter dataFormat = new DataFormatter();
        try {
            in = DataProviderSet_01.class.getClassLoader().getResourceAsStream("testDataFormExcel001.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(in);

            Sheet sheet = workbook.getSheet("學生");

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
                datas.add(new Object[]{rowMap});
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
