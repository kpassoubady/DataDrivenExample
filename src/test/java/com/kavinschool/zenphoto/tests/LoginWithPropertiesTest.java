/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.tests;

import com.kavinschool.zenphoto.base.BaseTest;
import com.kavinschool.zenphoto.common.ZenPhotoCommon;
import com.kavinschool.zenphoto.utils.Constants;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * The Class LoginWithPropertiesTest.
 */
public class LoginWithPropertiesTest extends BaseTest {
	
	/** The user ID. */
	private String userId;
	
	/** The password. */
	private String password;

	/**
	 * Sets the up base info.
	 *
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@BeforeMethod(alwaysRun=true)
	public void setUpUserInfo() throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(Constants.SERV_PROP_FILE));

		userId = props.getProperty("userid");
		password = props.getProperty("password");
	}

	/**
	 * Test login.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	@Test(groups = {"props"})
	public void testLogin() throws InterruptedException  {
		new ZenPhotoCommon(driver).doZenPhotoUserTest(baseUrl, userId, password);
	}

}
