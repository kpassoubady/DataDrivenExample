/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.tests;

import com.kavinschool.zenphoto.base.BaseTest;
import com.kavinschool.zenphoto.common.ZenPhotoCommon;
import com.kavinschool.zenphoto.utils.Constants;
import com.kavinschool.zenphoto.utils.ExcelUtils;
import com.kavinschool.zenphoto.utils.ExcelUtils.UserInfoColumns;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * The Class LoginWithExcelTest.
 */
public class LoginWithExcelTest extends BaseTest {


	/**
	 * Test excel login.
	 *
	 */
	@Test(groups = {"excel"})
	public void testExcelLogin()  {

		System.out.println("testExcelLogin - Reading the user id, password from an Excel file");
		try {
			// Read Excel Data
			List<List<HSSFCell>> testData = ExcelUtils.ReadExcelData(Constants.USER_XLS_FILE);
			System.out.println("Able to read from Excel file and got the user id/password...!");
			HSSFCell cell;

			Assert.assertFalse(testData.isEmpty(), "Excel is empty");

			for (int row = 1; row < testData.size(); row++) {
				List<?> list = testData.get(row);
				cell = (HSSFCell) list.get(UserInfoColumns.USERID.ordinal());
				final String userId = cell.getRichStringCellValue().getString();
				cell = (HSSFCell) list.get(UserInfoColumns.PASSWORD.ordinal());
				final String password = cell.getRichStringCellValue().getString();
				System.out.println("Gathered from Excel:" + userId + "," + password);
				new ZenPhotoCommon(driver).doZenPhotoUserTest(baseUrl, userId, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
