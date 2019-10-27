package com.kavinschool.zenphoto.tests;

import org.testng.annotations.Test;

import java.util.Random;

public class MultiThreadTest {

    @Test(threadPoolSize = 3, invocationCount = 10, groups = {"multi"})
    public void testRandomNumbers() {
        long threadId = Thread.currentThread().getId();
        System.out.println("threadId = " + threadId);
        Random rand = new Random();
        int currentRand = rand.nextInt(1000);
        System.out.println("currentRand = " + currentRand);
        //Assert.assertTrue(currentRand%2==0,"Not divisible by 3");
    }
}
