package com.kavinschool.zenphoto.tests;

import com.kavinschool.zenphoto.base.BaseTest;
import com.kavinschool.zenphoto.common.ZenPhotoCommon;
import com.kavinschool.zenphoto.data.ZenPhotoDataProvider;
import org.testng.annotations.Test;


/**
 * The type Login with excel data provider.
 */
public class LoginWithExcelDataProvider extends BaseTest {

	/**
	 * Test excel login.
	 *
	 * @param userId   the user id
	 * @param password the password
	 * @throws Exception the exception
	 */
	@Test(dataProvider = "userInfoFromExcel",groups = {"exceldp"}, dataProviderClass = ZenPhotoDataProvider.class)
	public void testExcelLogin(String userId, String password) throws Exception {
		new ZenPhotoCommon(driver).doZenPhotoUserTest(baseUrl, userId, password);
	}

}
