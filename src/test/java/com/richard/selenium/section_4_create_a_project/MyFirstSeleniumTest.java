package com.richard.selenium.section_4_create_a_project;

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

        //WebDriver is an interface. I could have easily have done:
        //HtmlUnitDriver driver = new HtmlUnitDriver();

        //Why use the interface then?
        //Well we may want to inject, in our tests, the driver, and have the driver itself instantiated somewhere else
        //because we may want our test to run across numerous different browsers (firefox, chrome etc).

        //Above - we use the Webdriver interface in the shopping-test-framework (and Magentys core). WebDriver
        //(interface) allows us to build one page object / one test (with a web driver variable) that we can then pass
        //in a web driver. Basically allows you to run the same test on a given page object in chrome, in firefox, in ie
        //(depending on the driver you pass into it). If we didn't use the webdriver interface (and instead used e.g
        //ChromeDriver driver = new ChromeDriver()), then that test can only run in chromedriver.
        //Using the webdriver interface gives us more flexibility to run the same test (using the same page object) on
        //multiple browsers.

        //Good photo example in my evernote from Marc - Webdriver interface example

        //some more info - https://code.google.com/p/selenium/wiki/NextSteps
        //https://code.google.com/p/selenium/wiki/GettingStarted
        //reference - 46 - My first test explored

        Assert.assertTrue("The page title is incorrect", driver.getTitle().startsWith("Welcome to tottenhamhotspur.com"));

        //So in the above, we are telling the driver to navigate to a specific website
        //And then we are checking whether the header tab states 'welcome to spurs'.
        //I imagine that because HtmlUnitDriver is a headless driver, the test simply checks the HTML within the DOM (check with others)

        //Tests run quickly with HtmlUnitDriver because there is no web browser
        //But, of course, you don't have the visibility of the test running

        //HtmlUnitDriver is great for checking the basics of pages - e.g. checking the title. Is very fast
        //Basically you should use a different driver depending on the type of test you are executing...
        //He will explain in more detail later on in the course.
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
