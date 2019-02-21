package com.util.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.apache.poi.ss.usermodel.Font.DEFAULT_CHARSET;

public class Test {

    public static void main(String[] args) {
        try (OutputStream ops = new FileOutputStream("D:\\book.xlsx")) {
//            File file = new File("C:\\Users\\BMF\\book.xlsx");
//            if (!file.exists()) {
//                file.createNewFile();
//            }
            Workbook workbook = new XSSFWorkbook();

            Sheet firstSheet = workbook.createSheet("first");
            firstSheet.setDefaultColumnWidth(256 * 10);
            Font font = workbook.createFont();
            font.setCharSet(DEFAULT_CHARSET);
            font.setColor(Short.valueOf("-9"));
            font.setFontHeight(Short.valueOf("255"));

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setBottomBorderColor(Short.valueOf("11"));
            cellStyle.setFont(font);
            firstSheet.setDefaultRowHeight(Short.valueOf("-1"));
            for (int i = 0; i < 10; i++) {
                Row firstSheetRow = firstSheet.createRow(i);
//            firstSheet.setColumnWidth(0, 256 * 10);

//                firstSheetRow.setHeight(Short.valueOf("-1"));

                for (int j = 0; j < 10; j++) {
                    Cell cell = firstSheetRow.createCell(j, CellType.STRING);
                    cell.setCellValue("first" + i * j);
                    cell.setCellStyle(cellStyle);
                }
            }
            firstSheet = workbook.createSheet();
            workbook.write(ops);
//            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
