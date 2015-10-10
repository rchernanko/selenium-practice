package com.richard.selenium.section_4_create_a_project;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GetPageGetTitleTest {

    @Test
    public void isBbcFootballWebsiteTitleCorrect() {

        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.bbc.co.uk/football/");
        String pageTitle = driver.getTitle();
        Assert.assertTrue("The page title is incorrect", pageTitle.startsWith("BBC Sport - Football"));
        driver.close();
    }

    @Test
    public void isArsenalWebsiteTitleCorrect() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.arsenal.com/");
        Assert.assertFalse("The page title is incorrect", driver.getTitle().startsWith("The worst club in the land"));
        driver.close();
    }

    //Using FirefoxDriver, ChromeDriver etc take longer to execute compared to the headless browser driver HtmlUnitDriver
    //Also, in the above, once the test has run, we are still left with a browser on the screen - this is bad!
    //This will be a killer if we have more tests!
    //So to close the browser windows, we can either use driver.close() or driver.quit()

    //driver.quit() will close all of the windows that are open (within your specific junit test)
    //driver.close() will close only the window that is open
    //My guess is that 9 times out of 10, you'd want to use the quit() method

    //Of course, you don't have to do this with HtmlUnitDriver because it is a headless driver (and so doesn't run in a browser)

    @Test
    public void isChannelFourWebsiteTitleCorrect() {

        WebDriver driver = new FirefoxDriver();
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

    //Of course, the above driver only closes if the test passes (i.e. it gets to a point where the JVM can execute
    //the driver.close() method.
    //If there is an error in the assert, the assertTrue method will throw an error and driver.close won't be executed

}
