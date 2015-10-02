package com.richard.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class MyFirstSeleniumTest {

    //In the webdriver world, the driver is the king!
    //Everything we do is based around a driver

    @Test
    public void driverIsTheKing() {

        //There are a bunch of drivers available to us within the Selenium library.
        //HtmlUnitDriver is a 'headless' driver - no browser is displayed when running the test
        //We also have FirefoxDriver, IeDriver, ChromeDriver, some mobile drivers + many, many more!

        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://www.spurs.co.uk/");

        Assert.assertTrue(driver.getTitle().startsWith("Welcome to tottenhamhotspur.com"));

        //So in the above, we are telling the driver to navigate to a specific website
        //And then we are checking whether the header tab states 'welcome to spurs'.
        //I imagine that because HtmlUnitDriver is a headless driver, the test simply checks the HTML within the DOM (check with others)

        //Tests run quickly with HtmlUnitDriver because there is no web browser
        //But, of course, you don't have the visibility of the test running
    }


    //Question re: the below - I want to unit test my method above - where should i keep my unit tests within my maven package?

    /*

    @Test
    public void driverIsTheKing() {

        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://www.spurs.co.uk/");

        Assert.assertFalse(driver.getTitle().startsWith("Welcome to Arsenal"));
    }

     */



}
