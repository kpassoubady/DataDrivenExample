/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.base;

import com.kavinschool.zenphoto.utils.Constants;
import com.kavinschool.zenphoto.utils.DriverFactory;
import com.kavinschool.zenphoto.utils.DriverUtils;
import com.kavinschool.zenphoto.utils.TestParameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * The Class BaseTest.
 */
public class BaseTest {

    /**
     * The driver.
     */
    protected WebDriver driver;

    /**
     * The base url.
     */
    protected String baseUrl;

    protected DriverUtils driverUtils;


    /**
     * Sets the up.
     */
    @BeforeMethod(alwaysRun=true)
    public void setUpDriver() throws Exception {
        DriverFactory driverFactory = new DriverFactory().setUpDriver();
        driver = driverFactory.getDriver();
        driverUtils = driverFactory.getDriverUtils();
    }

    /**
     * Sets the up base info.
     *
     * @throws FileNotFoundException the file not found exception
     * @throws IOException           Signals that an I/O exception has occurred.
     */
    @BeforeMethod(alwaysRun=true)
    public void setUpBaseInfo() throws FileNotFoundException, IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(Constants.SERV_PROP_FILE));

        baseUrl = props.getProperty("url_ks");
    }

    /**
     * Tear down.
     */
    @AfterMethod(alwaysRun=true)
    public void tearDownDriver() {
        //driver.close();
        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void testData(Method method, Object[] testData) {
        String testCase = "";
        if (testData != null && testData.length > 0) {
            TestParameters testParams = null;
            String _dyna_name = null;
            //Check if test method has actually received required parameters
            for (Object testParameter : testData) {
                if (testParameter instanceof TestParameters) {
                    testParams = (TestParameters) testParameter;
                    break;
                }
                if (testParameter instanceof String) {
                    _dyna_name = (String) testParameter;
                    break;
                }
            }
            if (testParams != null) {
                testCase = testParams.getTestName();
            }
            if(_dyna_name!=null){
                testCase = _dyna_name;
            }
        }
        System.setProperty("mTestCaseName",String.format("%s(%s)", method.getName(), testCase));

    }
}
