package com.util.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by BMF on 2017/11/8.
 */
public class PoiUtils {
    public static void main(String[] args) {
//        writeFile("D:\\excel\\zhexin.xls");
        efficientWriteFile("D:\\excel/sxssf.xlsx");
    }

    /**
     * 小量数据导入
     *
     * @param file 输出文件地址
     */
    public static void writeFile(String file) {
        try (OutputStream ops = new FileOutputStream(file)) {
            //创建Excel
            Workbook workbook = new HSSFWorkbook();
            //创建单元格格式
            CellStyle cellStyleHeader = workbook.createCellStyle();
            CellStyle cellStyleText = workbook.createCellStyle();
            //创建字体
            Font headerFont = workbook.createFont();
            headerFont.setCharSet(Font.DEFAULT_CHARSET);
            headerFont.setColor(Font.COLOR_NORMAL);
            headerFont.setFontHeight((short) 255);
            cellStyleHeader.setFont(headerFont);
            Font textFont = workbook.createFont();
            textFont.setColor(Font.COLOR_NORMAL);
            textFont.setFontHeight((short) 192);
            textFont.setCharSet(Font.DEFAULT_CHARSET);
            cellStyleText.setFont(textFont);
            for (int k = 0; k < 10; k++) {
                //创建一页
                Sheet sheet = workbook.createSheet("zhexin" + k);
                sheet.setDefaultRowHeight((short) 255);
                sheet.setDefaultColumnWidth(16);
                for (int j = 0; j < 10; j++) {
                    //创建行
                    Row row = sheet.createRow(j);
                    for (int i = 0; i < 10; i++) {
                        //创建单元格
                        Cell cell = row.createCell(i, CellType.STRING);
                        cell.setCellValue("折信测试");
                        if (j == 0) {
//                        sheet.autoSizeColumn(i);
                            cell.setCellStyle(cellStyleHeader);
                        } else {
                            cell.setCellStyle(cellStyleText);
                        }
                    }
                }
            }
            workbook.write(ops);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void efficientWriteFile(String file) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
            wb.setCompressTempFiles(true); // temp files will be gzipped
//            Sheet sh = wb.createSheet();
            //创建单元格格式
            CellStyle cellStyleHeader = wb.createCellStyle();
            CellStyle cellStyleText = wb.createCellStyle();
            //创建字体
            Font headerFont = wb.createFont();
            headerFont.setCharSet(Font.DEFAULT_CHARSET);
            headerFont.setColor(Font.COLOR_NORMAL);
            headerFont.setFontHeight((short) 255);
            cellStyleHeader.setFont(headerFont);
            Font textFont = wb.createFont();
            textFont.setColor(Font.COLOR_NORMAL);
            textFont.setFontHeight((short) 192);
            textFont.setCharSet(Font.DEFAULT_CHARSET);
            cellStyleText.setFont(textFont);

            for (int k = 0; k < 10; k++) {
                //创建一页
                Sheet sheet = wb.createSheet("zhexin" + k);
                sheet.setDefaultRowHeight((short) 255);
                sheet.setDefaultColumnWidth(16);
                for (int rownum = 0; rownum < 1000; rownum++) {
                    Row row = sheet.createRow(rownum);
                    for (int cellnum = 0; cellnum < 10; cellnum++) {
                        Cell cell = row.createCell(cellnum);
                        String address = new CellReference(cell).formatAsString();
                        cell.setCellValue(address);
                        if (rownum == 0) {
//                        sheet.autoSizeColumn(i);
                            cell.setCellStyle(cellStyleHeader);
                        } else {
                            cell.setCellStyle(cellStyleText);
                        }
                    }
                }
            }

//            // Rows with rownum < 900 are flushed and not accessible
//            for (int rownum = 0; rownum < 900; rownum++) {
//                Assert.assertNull(sh.getRow(rownum));
//            }
//
//            // ther last 100 rows are still in memory
//            for (int rownum = 900; rownum < 1000; rownum++) {
//                Assert.assertNotNull(sh.getRow(rownum));
//            }

            wb.write(out);
            out.close();
            // dispose of temporary files backing this workbook on disk
            wb.dispose();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
