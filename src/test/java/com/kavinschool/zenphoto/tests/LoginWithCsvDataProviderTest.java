/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.tests;

import com.kavinschool.zenphoto.base.BaseTest;
import com.kavinschool.zenphoto.common.ZenPhotoCommon;
import com.kavinschool.zenphoto.data.ZenPhotoDataProvider;
import org.testng.annotations.Test;


/**
 * The type Login with csv data provider test.
 */
public class LoginWithCsvDataProviderTest extends BaseTest {


	/**
	 * Test csv login.
	 *
	 * @param userId   the user id
	 * @param password the password
	 * @throws Exception the exception
	 */
	@Test(dataProvider = "userInfoFromCsv", groups = {"csvdp"}, dataProviderClass = ZenPhotoDataProvider.class)
	public void testCsvLogin(String userId, String password) throws Exception {
		new ZenPhotoCommon(driver).doZenPhotoUserTest(baseUrl, userId, password);
	}
}
