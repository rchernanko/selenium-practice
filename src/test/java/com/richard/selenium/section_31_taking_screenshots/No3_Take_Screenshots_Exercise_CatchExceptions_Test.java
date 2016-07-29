package com.richard.selenium.section_31_taking_screenshots;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

public class No3_Take_Screenshots_Exercise_CatchExceptions_Test {

    /*

    Another method of taking screenshots is to use a try / catch block

    */

    private WebDriver driver;

    @Test
    public void canWeTakeAScreenshotExceptionStyleChromeDriver() {

        driver = new ChromeDriver();
        driver.get("http://seleniumsimplified.com");

        try {

            TakesScreenshot snapper = (TakesScreenshot) driver;
            File temporaryImageFile = snapper.getScreenshotAs(OutputType.FILE);

            assertThat(temporaryImageFile.length(), is(greaterThan(0L)));

            //Use these below lines in debug mode (to find out where the file is - remember if i want to store it somewhere,
            //I have to move the file myself

            System.out.println("Temp file written to : " + temporaryImageFile.getAbsolutePath());
            //i can also get the driver to display the screenshot in the browser - as below...
            driver.get("File://" + temporaryImageFile.getAbsolutePath());

        } catch (ClassCastException e) {
            //if we cannot cast the driver to TakesScreenshot we will get a ClassCastException
            System.out.println(e);
            fail("Driver did not support screenshots");
        }
    }

    @Test
    public void canWeTakeAScreenshotExceptionStyleHtmlUnitDriver() {

        driver = new HtmlUnitDriver();
        driver.get("http://seleniumsimplified.com");

        try {

            TakesScreenshot snapper = (TakesScreenshot) driver;
            fail("Expected htmlunitdriver to NOT cast to takes screenshot");

        } catch (ClassCastException e) {
            //if we cannot cast the driver to TakesScreenshot we will get a ClassCastException
            System.out.println(e);
        }

    }


    @After
    public void quitDriver() {
        driver.quit();
    }
}
