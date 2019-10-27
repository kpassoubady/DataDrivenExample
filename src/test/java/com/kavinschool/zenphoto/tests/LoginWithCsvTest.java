/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.tests;

import au.com.bytecode.opencsv.CSVReader;
import com.kavinschool.zenphoto.base.BaseTest;
import com.kavinschool.zenphoto.common.ZenPhotoCommon;
import com.kavinschool.zenphoto.utils.Constants;
import org.testng.annotations.Test;

import java.io.FileReader;

/**
 * The Class LoginWithCSV.
 */
public class LoginWithCsvTest extends BaseTest {

	/** The user ID. */
	private String userId;

	/** The password. */
	private String password;

	/**
	 * Test csv login.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(groups = {"csv"})
	public void testCsvLogin() throws Exception {
		System.out.println("testCsvLogin - Reading the user id, password from a CSV file");
		try {
			CSVReader reader = new CSVReader(new FileReader(Constants.USER_CSV_FILE));
			String[] nextLine;
			nextLine = reader.readNext();

			while ((nextLine = reader.readNext()) != null) {
				System.out.println(nextLine[0] + "," + nextLine[1]);
				userId = nextLine[0];
				password = nextLine[1];
				new ZenPhotoCommon(driver).doZenPhotoUserTest(baseUrl, userId, password);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
