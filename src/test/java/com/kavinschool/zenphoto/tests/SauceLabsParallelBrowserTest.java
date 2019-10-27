/*
 *
 *  * Author: Kangeyan Passoubady
 *  * (c) Kavin School
 *
 */

package com.kavinschool.zenphoto.tests;

import com.kavinschool.zenphoto.base.SauceBaseTest;
import com.kavinschool.zenphoto.common.ZenPhotoCommon;
import com.kavinschool.zenphoto.utils.SauceUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The type Sauce labs data provider test.
 */
public class SauceLabsParallelBrowserTest extends SauceBaseTest {

    /**
     * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    /**
     * DataProvider that explicitly sets the browser combinations to be used.
     *
     * @param testMethod the test method
     * @return Two dimensional array of objects with browser, version, and platform information
     */
    @DataProvider(name = "browsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{
                //new Object[]{"MicrosoftEdge", "18.17763", "Windows 10"},
                new Object[]{"firefox", "latest", "Windows 8.1"},
                new Object[]{"internet explorer", "11.0", "Windows 8.1"},
                //new Object[]{"safari", "12", "macOS 10.13"},
                new Object[]{"chrome", "latest", "macOS 10.13"},
                //new Object[]{"firefox", "latest-1", "Windows 10"},
        };
    }

    /**
     * Gets web driver.
     *
     * @return the {@link WebDriver} for the current thread
     */
    public WebDriver getWebDriver() {
        return webDriver.get();
    }

    /**
     * Gets session id.
     *
     * @return the Sauce Job id for the current thread
     */
    public String getSessionId() {
        return sessionId.get();
    }

    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the browser,
     * version and os parameters, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the  instance.
     *
     * @param browser    Represents the browser to be used as part of the test run.
     * @param version    Represents the version of the browser to be used as part of the test run.
     * @param os         Represents the operating system to be used as part of the test run.
     * @param methodName Represents the name of the test case that will be used to identify the test on Sauce.
     * @return
     * @throws MalformedURLException if an error occurs parsing the url
     */
    protected void createDriver(String browser, String version, String os, String methodName)
            throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // set desired capabilities to launch appropriate browser on Sauce
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        // The CapabilityType.PLATFORM_NAME does not work with Sauce yet
        //capabilities.setCapability(CapabilityType.PLATFORM_NAME, os);
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        if (methodName != null) {
            capabilities.setCapability("name", methodName);
        }
        if (BUILD_TAG != null) {
            capabilities.setCapability("build", BUILD_TAG);
        } else {
            capabilities.setCapability("build", "SauceLabsDataProviderTest");
        }

        // Launch remote browser and set it as the current thread
        webDriver.set(new RemoteWebDriver(
                new URL(SAUCE_URL_WITH_AUTH),
                capabilities));

        // set current sessionId
        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);
    }

    /**
     * Test zen photo user.
     *
     * @param browser the browser
     * @param version the version
     * @param os      the os
     * @param method  the method
     * @throws MalformedURLException the malformed url exception
     * @throws InterruptedException  the interrupted exception
     */
    @Test(dataProvider = "browsers", groups = {"sauce-browsers"})
    public void testParallelZenPhotoUser(String browser, String version, String os, Method method) throws MalformedURLException, InterruptedException {
        this.createDriver(browser, version, os, method.getName());

        new ZenPhotoCommon(this.getWebDriver()).doZenPhotoUserTest(baseUrl, userId, password);
    }

    /**
     * Method that gets invoked after test.
     * Closes the browser
     *
     * @param result the result
     * @throws Exception the exception
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        SauceUtils.updateTest(getSessionId(), result);
        webDriver.get().quit();
    }

}
