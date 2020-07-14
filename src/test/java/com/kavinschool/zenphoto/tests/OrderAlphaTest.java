package com.kavinschool.zenphoto.tests;

import org.testng.Assert;
import org.testng.ITest;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.kavinschool.zenphoto.base.PdfBaseTest;

public class OrderAlphaTest  extends PdfBaseTest implements ITest {
    protected String mTestCaseName = "";

    @Test(groups = {"order"})
    public void testApple() {
        System.out.println("OrderAlphaTest @Test testApple");
    }

    @Test(groups = {"order"})
    public void testBanana() {
        System.out.println("OrderAlphaTest @Test testBanana");
    }

    @Test(groups = {"order"})
    public void testCherry() {
        System.out.println("OrderAlphaTest @Test testCherry");
    }

    @Test(groups = {"order"})
    public void testDate() {
        System.out.println("OrderAlphaTest @Test testDate");
        throw new SkipException("Skipped for fun!!");
    }

    @Test(groups = {"order"})
    public void testEggPlant() {
        System.out.println("OrderAlphaTest @Test testEggPlant");
        Assert.assertEquals(10,20, "Number comparison");
    }

    @Override
    public String getTestName() {
        mTestCaseName = System.getProperty("mTestCaseName");
        return this.mTestCaseName;
    }

}
