/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.tests;

import com.kavinschool.zenphoto.base.BaseTest;
import com.kavinschool.zenphoto.common.ZenPhotoCommon;
import com.kavinschool.zenphoto.data.ZenPhotoDataProvider;
import org.testng.ITest;
import org.testng.annotations.Test;


/**
 * The type Login with csv data provider generate pdf test.
 */
public class LoginWithCsvDataProviderGeneratePdfTest extends BaseTest implements ITest {

	/**
	 * The M test case name.
	 */
	protected String mTestCaseName = "";

	/**
	 * Test csv login.
	 *
	 * @param userId   the user id
	 * @param password the password
	 * @throws Exception the exception
	 */
	@Test(dataProvider = "userInfoFromCsv", groups = {"pdfdp"}, dataProviderClass = ZenPhotoDataProvider.class)
	public void testCsvLogin(String userId, String password) throws Exception {
		new ZenPhotoCommon(driver).doZenPhotoUserTest(baseUrl, userId, password);
	}

	@Override
	public String getTestName() {
		mTestCaseName = System.getProperty("mTestCaseName");
		return this.mTestCaseName;
	}

}
