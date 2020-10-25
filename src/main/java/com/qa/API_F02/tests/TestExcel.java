package com.qa.API_F02.tests;

import  java.io.FileInputStream;
import  java.io.IOException;
import  java.io.InputStream;
import  java.text.DecimalFormat;

import  org.apache.commons.lang3.StringUtils;
import  org.apache.poi.hssf.usermodel.HSSFDateUtil;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.ss.usermodel.Cell;
import  org.apache.poi.ss.usermodel.FormulaEvaluator;
import  org.apache.poi.ss.usermodel.Row;
import  org.apache.poi.ss.usermodel.Sheet;

//import  landprice.util.DateUtil;

/**
 * @author urPaPa
 * @date 2020/9/18 10:26
 */
public class TestExcel {
    private  static  FormulaEvaluator evaluator;
    public  static  void  main(String[] args)  throws  IOException {
        InputStream is= new  FileInputStream( "E:/temp/tempfile/1.xls" );
        HSSFWorkbook wb= new  HSSFWorkbook(is);
        Sheet sheet=wb.getSheetAt( 0 );

        evaluator=wb.getCreationHelper().createFormulaEvaluator();

        for  ( int  i =  0 ; i < 1 ; i++) {
            Row  row=sheet.getRow(i);
            for  (Cell cell : row) {
//                System.out.println(getCellValueByCell(cell));
            }
        }
        is.close();
    }

    //获取单元格各类型值，返回字符串类型
//    private  static  String getCellValueByCell(Cell cell) {
        //判断是否为null或空串
//        if  (cell== null  || cell.toString().trim().equals( "" )) {
//            return  "" ;
//        }
//        String cellValue =  "" ;
//        int  cellType=cell.getCellType();
//        if (cellType==Cell.CELL_TYPE_FORMULA){  //表达式类型
//            cellType=evaluator.evaluate(cell).getCellType();
//        }
//
//        switch  (cellType) {
//            case  Cell.CELL_TYPE_STRING:  //字符串类型
//                cellValue= cell.getStringCellValue().trim();
//                cellValue=StringUtils.isEmpty(cellValue) ?  ""  : cellValue;
//                break ;
//            case  Cell.CELL_TYPE_BOOLEAN:   //布尔类型
//                cellValue = String.valueOf(cell.getBooleanCellValue());
//                break ;
//            case  Cell.CELL_TYPE_NUMERIC:  //数值类型
//                if  (HSSFDateUtil.isCellDateFormatted(cell)) {   //判断日期类型
//                    cellValue =    DateUtil.formatDateByFormat(cell.getDateCellValue(),  "yyyy-MM-dd" );
//                }  else  {   //否
//                    cellValue =  new  DecimalFormat( "#.######" ).format(cell.getNumericCellValue());
//                }
//                break ;
//            default :  //其它类型，取空串吧
//                cellValue =  "" ;
//                break ;
//        }
//        return  cellValue;
//    }
}
