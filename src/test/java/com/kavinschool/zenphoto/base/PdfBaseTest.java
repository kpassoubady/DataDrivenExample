/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.kavinschool.zenphoto.utils.Constants;
import com.kavinschool.zenphoto.utils.DriverFactory;
import com.kavinschool.zenphoto.utils.DriverUtils;
import com.kavinschool.zenphoto.utils.TestParameters;

public class PdfBaseTest {

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
