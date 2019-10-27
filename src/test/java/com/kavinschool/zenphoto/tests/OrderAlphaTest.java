package com.kavinschool.zenphoto.tests;

import org.testng.annotations.Test;

public class OrderAlphaTest {
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
}
