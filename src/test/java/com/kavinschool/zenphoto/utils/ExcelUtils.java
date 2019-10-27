/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.Assert;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Class ExcelUtils.
 */
public class ExcelUtils {

	/**
	 * Read excel data.
	 *
	 * @param excelFileName the excel file name
	 * @return the list
	 * @throws Exception the exception
	 */
	public static List<List<HSSFCell>> ReadExcelData(String excelFileName) throws Exception {
		List<List<HSSFCell>> workSheetData = new ArrayList<>();

		//
		// Create a FileInputStream that will be use to read the
		// excel file.
		//
		try (FileInputStream fis = new FileInputStream(excelFileName)) {
			//
			// Create an excel workbook from the file system.
			//
			try (HSSFWorkbook workbook = new HSSFWorkbook(fis)) {
				//
				// Get the first sheet on the workbook.
				//
				HSSFSheet firstSheet = workbook.getSheetAt(0);
				//
				// When we have a sheet object in hand we can use iterator on
				// each sheet's rows and on each row's cells. We store the
				// data read on an ArrayList so that we can printed the
				// content of the excel to the console.
				//
				Iterator<?> rows = firstSheet.rowIterator();
				while (rows.hasNext()) {
					HSSFRow row = (HSSFRow) rows.next();
					Iterator<?> cells = row.cellIterator();

					List<HSSFCell> data = new ArrayList<>();
					while (cells.hasNext()) {
						HSSFCell cell = (HSSFCell) cells.next();
						data.add(cell);
					}
					workSheetData.add(data);
				}
			}
		}
		return workSheetData;
	}

	/**
	 * The Enum UserInfoColumns.
	 */
	public enum UserInfoColumns {
		// Define ExcelSheet Columns, the order in which columns
		// listed in the excel, the same order needs to be listed below
		/** The userid. */
		USERID,
		/** The password. */
		PASSWORD,
	}

	/**
	 * Gets the 2 d excel data.
	 *
	 * @return the 2 d excel data
	 * @throws Exception the exception
	 */
	public static String[][] get2dExcelData(String excelFileName) throws Exception {
		// Read Excel Data
		List<List<HSSFCell>> testData = ExcelUtils.ReadExcelData(excelFileName);
		// Proceed only if Excel has some values
		Assert.assertFalse(testData.isEmpty(), "Excel is empty");

		// Convert the list into Object[][]
		String[][] dataInfo = new String[testData.size() - 1][];

		// Ignore the header row, start from 1st row
		for (int row = 1; row < testData.size(); row++) {
			List<?> list = testData.get(row);
			HSSFCell cell = (HSSFCell) list.get(UserInfoColumns.USERID.ordinal());
			String userID = cell.getRichStringCellValue().getString();
			cell = (HSSFCell) list.get(UserInfoColumns.PASSWORD.ordinal());
			String password = cell.getRichStringCellValue().getString();
			System.out.println("Gathered from Excel:" + userID + " -> " + password);
			// Create a row Object
			String[] rowInfo = new String[] { userID, password };
			// Store starting from 0
			dataInfo[row - 1] = rowInfo;
		}
		return dataInfo;
	}
}
