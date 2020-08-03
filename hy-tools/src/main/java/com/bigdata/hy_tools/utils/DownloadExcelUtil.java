package com.bigdata.hy_tools.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.OutputStream;

public class DownloadExcelUtil {

    public static void exportExcel(OutputStream outputStream) throws Exception{
		//可选择模板是.xls格式还是.xlsx格式
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个sheet表，可设置多个sheet
        Sheet sheet = workbook.createSheet("sheet1");
        sheet.setColumnWidth(0,8000 );
        sheet.setColumnWidth(1,8000 );
        sheet.setColumnWidth(2,8000 );

        String []tableHeader = new String[] {"工号","手机号","身份证号"};
        HSSFRow firstRow = (HSSFRow) sheet.createRow((short) 0);
        for(int i = 0; i < tableHeader.length; i++){
            HSSFCell cell = firstRow.createCell((short)i);
            cell.setCellStyle(workbook.createCellStyle());
            cell.setCellValue(tableHeader[i]);
        }
        workbook.write(outputStream);
        System.out.println(outputStream);
        outputStream.flush();

    }

}
