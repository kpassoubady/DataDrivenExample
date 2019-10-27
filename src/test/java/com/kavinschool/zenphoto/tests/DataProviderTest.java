package com.kavinschool.zenphoto.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The type Data provider test.
 */
public class DataProviderTest {

    /**
     * Alphabet data object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "alphabetData")
    public Object[][] alphabetData() {
        return new Object[][] {
                {"a", "apple","ant"},
                {"b", "banana","bear"},
                {"c", "cherry","cat"},
                {"d", "cherry","date"},
                {"e", "eggplant","date"},
        };
    }

    /**
     * Test alphabets.
     *
     * @param letter the letter
     * @param fruit  the fruit
     * @param animal the animal
     */
    @Test(dataProvider = "alphabetData")
    public void testAlphabets(String letter, String fruit, String animal) {
        System.out.println("letter = [" + letter + "], fruit = [" + fruit + "], animal = [" + animal + "]");
        Assert.assertTrue(fruit.startsWith(letter), fruit + " does not start with " + letter );
        Assert.assertTrue(animal.startsWith(letter), animal + " does not start with " + letter );
    }
}
