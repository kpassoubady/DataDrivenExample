package com.kavinschool.zenphoto.tests;

import com.kavinschool.zenphoto.base.SauceBaseTest;
import com.kavinschool.zenphoto.common.ZenPhotoCommon;
import com.kavinschool.zenphoto.utils.DriverUtils;
import com.kavinschool.zenphoto.utils.SauceUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


/**
 * The type Sauce labs test.
 */
public class SauceLabsTest  extends SauceBaseTest {
    private WebDriver driver;

    /**
     * Sets up.
     *
     * @throws MalformedURLException the malformed url exception
     */
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        System.out.println("SauceLabsTest.setUp");

        /**
         * * Here we set the MutableCapabilities for "sauce:options", which is required for newer versions of Selenium
         * and the w3c protocol, for more info read the documentation:
         * https://wiki.saucelabs.com/display/DOCS/Selenium+W3C+Capabilities+Support+-+Beta */
        MutableCapabilities sauceOpts = new MutableCapabilities();
        sauceOpts.setCapability("username", SAUCE_USER_NAME);
        sauceOpts.setCapability("accessKey", SAUCE_ACCESS_KEY);
        /** In order to use w3c you must set the seleniumVersion **/
        sauceOpts.setCapability("seleniumVersion", "3.141.59");
        sauceOpts.setCapability("name", "SauceLabsTest");

        /**
         * in this exercise we set additional capabilities below that align with
         * testing best practices such as tags, timeouts, and build name/numbers.
         *
         * Tags are an excellent way to control and filter your test automation
         * in Sauce Analytics. Get a better view into your test automation.
         */
        List<String> tags = Arrays.asList("sauceDemo", "demoTest", "zenPhoto", "TestNG Test");
        sauceOpts.setCapability("tags", tags);
        /** Another of the most important things that you can do to get started
         * is to set timeout capabilities for Sauce based on your organizations needs. For example:
         * How long is the whole test allowed to run?*/
        sauceOpts.setCapability("maxDuration", 3600);
        /** A Selenium crash might cause a session to hang indefinitely.
         * Below is the max time allowed to wait for a Selenium command*/
        sauceOpts.setCapability("commandTimeout", 600);
        /** How long can the browser wait for a new command */
        sauceOpts.setCapability("idleTimeout", 1000);

        /** Setting a build name is one of the most fundamental pieces of running
         * successful test automation. Builds will gather all of your tests into a single
         * 'test suite' that you can analyze for results.
         * It's a best practice to always group your tests into builds. */
        sauceOpts.setCapability("build", "DemoSauceLabsTest");

        /** Required to set w3c protocol **/
        ChromeOptions chromeOpts = new ChromeOptions();
        chromeOpts.setExperimentalOption("w3c", true);

        /** Set a second MutableCapabilities object to pass Sauce Options and Chrome Options **/
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("sauce:options", sauceOpts);
        capabilities.setCapability("goog:chromeOptions", chromeOpts);
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("platformVersion", "Windows 10");
        capabilities.setCapability("browserVersion", "latest");

        driver = new RemoteWebDriver(new URL(SAUCE_URL), capabilities);

    }


    /**
     * Test zen photo user test.
     *
     * @throws InterruptedException the interrupted exception
     */
    @Test(groups = {"sauce"})
    public void testZenPhotoUserTest() throws InterruptedException {
        SauceUtils.annotate(driver, "Zen Photo User Login Test Started");
        new ZenPhotoCommon(driver).doZenPhotoUserTest(baseUrl, userId, password);
        SauceUtils.annotate(driver, "Zen Photo User Login Test Finished");
    }

    /**
     * Below we are performing 2 critical actions. Quitting the driver and passing
     * the test result to Sauce Labs user interface.
     *
     * @param result the result
     */
    @AfterMethod
    public void cleanUpAfterTestMethod(ITestResult result) {
        System.out.println("SauceLabsTest.cleanUpAfterTestMethod");

        //API way to set the job result
        SauceUtils.updateTest(new DriverUtils(driver).getSessionId(), result);

        driver.quit();
    }

}
