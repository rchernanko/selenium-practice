/*

Read this class third.

 */

package com.richard.selenium.section_7_junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class No3_MoreAssertPracticeTest {

    String itemOfClothing;
    int size;
    static double price;
    //Remember that 'price' has to be static because it is being accessed by static methods :-)

    @BeforeClass
    public static void setPrice() {
        price = 12.3;
    }

    @Before
    public void setItemOfClothing() {
        itemOfClothing = "jeans";
    }

    @Test
    public void isItemOfClothingNameCorrectUsingAssertTrue() {
        Assert.assertTrue("The item of clothing name is not correct", itemOfClothing.equals("jeans"));
    }

    @Test
    public void isItemOfClothingNameCorrectUsingAssertFalse() {
        Assert.assertFalse("The item of clothing name is correct", itemOfClothing.equals("trousers"));
    }

    @Test
    public void isItemSizeCorrect() {
        size = 12;
        Assert.assertThat("The size is not correct", size, is(12));
    }

    @Test
    public void isPriceCorrect() {
        Assert.assertThat("Price is not correct", price, is(not(13.5)));
    }

}
