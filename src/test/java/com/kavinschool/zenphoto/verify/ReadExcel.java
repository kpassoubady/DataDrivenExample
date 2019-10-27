/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.verify;

import com.kavinschool.zenphoto.utils.Constants;
import com.kavinschool.zenphoto.utils.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Class ReadExcel.
 */
public class ReadExcel {

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        //
        // An excel file name. You will be reading from this file
        //

        //
        // Create an ArrayList to store the data read from excel sheet.
        //
        List<List<HSSFCell>> sheetData = new ArrayList<List<HSSFCell>>();
        //
        // Create a FileInputStream that will be use to read the
        // excel file.
        //
        try (FileInputStream fis = new FileInputStream(Constants.CAR_XLS_FILE)) {
            //
            // Create an excel workbook from the file system.
            //
            try (HSSFWorkbook workbook = new HSSFWorkbook(fis)) {
                //
                // Get the first sheet on the workbook.
                //
                HSSFSheet sheet = workbook.getSheetAt(0);
                // workbook.getSheet("Advertisement");
                //
                // When we have a sheet object in hand we can use iterator on
                // each sheet's rows and on each row's cells. We store the
                // data read on an ArrayList so that we can print the
                // content of the excel to the console.
                //
                Iterator<?> rows = sheet.rowIterator();
                while (rows.hasNext()) {
                    HSSFRow row = (HSSFRow) rows.next();
                    Iterator<?> cells = row.cellIterator();

                    List<HSSFCell> data = new ArrayList<HSSFCell>();
                    while (cells.hasNext()) {
                        HSSFCell cell = (HSSFCell) cells.next();
                        data.add(cell);
                    }

                    sheetData.add(data);
                }
            }
        }
        printExcelData(sheetData);
    }

    /**
     * Prints the excel data.
     *
     * @param sheetData the sheet data
     */
    private static void printExcelData(List<List<HSSFCell>> sheetData) {
        // Looks for numeric or text values and handles appropriately
        // Prints the excel sheet values
        // Iterates the data and print it out to the console.
        for (int i = 0; i < sheetData.size(); i++) {
            List<?> list = sheetData.get(i);
            for (int j = 0; j < list.size(); j++) {
                HSSFCell cell = (HSSFCell) list.get(j);
                // System.out.print("Cell Type" + cell.getCellType() + " ");
                // Handle Text Values
                if (cell.getCellType() == CellType.STRING) {
                    System.out.print(cell.getRichStringCellValue().getString());
                } else if (cell.getCellType() == CellType.NUMERIC) {

                    // Handle Numeric Values
                    System.out.print(StringUtils.customFormat("$#,###.00", cell.getNumericCellValue()));
                }
                // Handle the last column
                // if last column don't print comma
                if (j < list.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("");
        }
    }

    public static void printExcel(List<List<HSSFCell>> sheetData) {
        for (var sheetRow : sheetData) {
            for (var cell : sheetRow) {
                if (cell.getCellType() == CellType.STRING) {
                    System.out.print(cell.getRichStringCellValue().getString());
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    // Handle Numeric Values
                    System.out.print(cell.getNumericCellValue());
                }
                System.out.print(", ");
            }
            System.out.println("");
        }
    }
}
