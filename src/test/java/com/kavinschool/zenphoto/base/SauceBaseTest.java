/*
 *
 *  * Author: Kangeyan Passoubady
 *  * (c) Kavin School
 *
 */

package com.kavinschool.zenphoto.base;

import com.kavinschool.zenphoto.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SauceBaseTest {
    /**
     * The Build tag.
     */
    protected final String BUILD_TAG = System.getenv("BUILD_TAG");

    /** Here we set environment variables from your local machine, or IntelliJ run configuration,
     *  and store these values in the variables below. Doing this is a best practice in terms of test execution
     *  and security. If you're not sure how to use env variables, refer to this guide -
     * https://wiki.saucelabs.com/display/DOCS/Best+Practice%3A+Use+Environment+Variables+for+Authentication+Credentials
     * or check junit5-README.md */
    /**
     * The Username.
     */
    protected final String SAUCE_USER_NAME = System.getenv("SAUCE_USERNAME");

    /**
     * The Access key.
     */
    protected final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");


    // USA
    protected final String SAUCE_URL = "https://ondemand.saucelabs.com/wd/hub";
    // EU Data Center
    // String sauceURL ="https://ondemand.eu-central-1.saucelabs.com/wd/hub";

    protected final String SAUCE_URL_WITH_AUTH = "https://" + SAUCE_USER_NAME + ":" + SAUCE_ACCESS_KEY + "@ondemand.saucelabs.com/wd/hub";

    /**
     * The user ID.
     */
    protected String userId;

    /**
     * The password.
     */
    protected String password;

    /**
     * The baseUrl.
     */
    protected String baseUrl;

    /**
     * Sets the up base info.
     *
     * @throws FileNotFoundException the file not found exception
     * @throws IOException           Signals that an I/O exception has occurred.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUpUserInfo() throws FileNotFoundException, IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(Constants.SERV_PROP_FILE));

        userId = props.getProperty("userid");
        password = props.getProperty("password");
        baseUrl = props.getProperty("url_ks");
    }

}
