package com.richard.selenium.section_4_create_a_project;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.firefox.MarionetteDriver;

public class No2_GetPageGetTitleTest {

    @Test
    public void isArsenalWebsiteTitleCorrect() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.arsenal.com/");
        Assert.assertFalse("The page title is incorrect", driver.getTitle().startsWith("The worst club in the land"));
        driver.close();
    }

    /*
    Using FirefoxDriver, ChromeDriver etc take longer to execute compared to the headless browser driver HtmlUnitDriver

    Also, in the above, once the test has run, we are still left with a browser on the screen - this is bad!
    This will be a killer if we have more tests!
    So to close the browser windows, we can either use driver.close() or driver.quit()

    driver.quit() will close all of the windows that are open
    driver.close() will close only the window that is open

    Of course, you don't have to do this with HtmlUnitDriver because it is a headless driver (and so doesn't run in a browser)

    */

    @Test
    public void isChannelFourWebsiteTitleCorrect() {

        /*

        If using Firefox Version 45 or below, I can use FirefoxDriver()

        However, if I am testing on Firefox Version 46 or above, I need to use another driver entitled
        'Marionette' - see readme

        For now, I will leave my firefox version at 45 for the intent of learning more webdriver quicker

         */

        WebDriver driver = new MarionetteDriver();
        driver.get("http://www.channel4.com/");
        Assert.assertTrue("The page title is incorrect", driver.getTitle().startsWith("On Demand"));
        driver.close();
    }

    @Test
    public void isHotmailWebsiteTitleCorrect() {

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.hotmail.com/");
        Assert.assertFalse("The page title is incorrect", driver.getTitle().startsWith("Sign in"));
        driver.close();
    }

    /*

    Of course, the above driver only closes if the test passes (i.e. it gets to a point where the JVM can execute
    the driver.close() method.
    If there is an error in the assert, the assertTrue method will throw an error and driver.close won't be executed

    */
}
