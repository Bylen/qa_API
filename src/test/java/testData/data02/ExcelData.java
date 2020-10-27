package testData.data02;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

/**
 * @author urPaPa
 * @date 2020/9/21 14:35
 */
@Slf4j
public class ExcelData {
    private XSSFSheet sheet;

    ExcelData(String filePath, String sheetName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            sheet = sheets.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据行和列的索引获取单元格的数据
     *
     * @param row    行号
     * @param column 列号
     */
    public String getExcelDateByIndex(int row, int column) {
        XSSFRow row1 = sheet.getRow(--row);
        String cell = row1.getCell(--column).toString();
        return cell;
    }

    /**
     * 根据某一列值为“******”的这一行，来获取该行第x列的值
     *
     * @param caseName      比较值
     * @param currentColumn 当前单元格列的索引
     * @param targetColumn  目标单元格列的索引
     */
    public String getCellByCaseName(String caseName, int currentColumn, int targetColumn) {
        String operateSteps = "";
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(--currentColumn).toString();
            if (cell.equals(caseName)) {
                operateSteps = row.getCell(--targetColumn).toString();
                break;
            }
        }
        return operateSteps;
    }

    /**
     * 打印excel数据
     */
    public void readExcelData(){
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for(int i=0;i<rows;i++){
            //获取列数
            XSSFRow row = sheet.getRow(i);
            int columns = row.getPhysicalNumberOfCells();
            for(int j=0;j<columns;j++){
                String cell = row.getCell(j).toString();
                System.out.println(cell);
            }
        }
    }

    //测试方法
    public static void main(String[] args){
        ExcelData sheet1 = new ExcelData("src/main/resources/excelData/testDataFormExcel.xlsx", "學生");
        String cell2 = sheet1.getExcelDateByIndex(7, 2);
        System.out.println(cell2);
        System.out.println("--------------------");
    }
}
