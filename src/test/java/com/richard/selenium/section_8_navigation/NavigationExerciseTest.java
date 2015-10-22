/*

Read this class second.

 */

package com.richard.selenium.section_8_navigation;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.CoreMatchers.*;

public class NavigationExerciseTest {

    private static WebDriver driver;
    private final String BASE_URL = "http://compendiumdev.co.uk";

    @BeforeClass
    public static void createInstanceOfWebdriver() {
        driver = new ChromeDriver();
    }

    @Test
    public void navigateToHomepage() {
        driver.navigate().to(BASE_URL);
        Assert.assertTrue("Page title is incorrect", driver.getTitle().matches("Software Testing Essays, Book Reviews and Information"));
    }

    @Test
    public void navigateToSeleniumPage() {
        driver.navigate().to(BASE_URL + "/selenium");
        Assert.assertThat("Page title is incorrect", driver.getTitle(), startsWith("Selenium Simplified"));

        driver.navigate().to(BASE_URL + "");


        driver.navigate().forward();
        Assert.assertThat("Page title is incorrect", driver.getTitle(), startsWith("Selenium Simplified"));

    }






    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}
