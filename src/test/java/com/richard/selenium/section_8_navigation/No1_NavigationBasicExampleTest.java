/*

Read this class first.

See evernote screenshots for introductory info on simple set of navigation commands:

Navigation section includes the basics like:

- get(url)
- navigate (to, back, forward, refresh

 */

package com.richard.selenium.section_8_navigation;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class No1_NavigationBasicExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriver() {
        driver = new FirefoxDriver();
    }

    @Test
    public void navigateWithNavigateTo() {
        driver.navigate().to("http://www.marksandspencer.com/");
        Assert.assertTrue(driver.getTitle().startsWith("Welcome to Marks & Spencer"));
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }


}
