package com.richard.selenium.section_31_taking_screenshots;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

public class No2_Take_Screenshots_Exercise_CheckingCapabilities_Test {

    /*

    One way to take screenshots is to first check that the driver has the capability to do so...

     */

    private WebDriver driver;

    @Test
    public void canWeTakeAScreenshotCapabilitiesStyleChromeDriver() {

        driver = new ChromeDriver();
        driver.get("http://seleniumsimplified.com");

        if(((HasCapabilities) driver).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)) {

            //if the driver does have the capability to take a screenshot, then come in here...if not, go straight
            //to the else block

            TakesScreenshot snapper = (TakesScreenshot) driver;
            File temporaryImageFile = snapper.getScreenshotAs(OutputType.FILE);

            //remember, than when we getScreenshotAs(OutputType.FILE), we obtain the screenshot into a temporary file
            //that will be deleted once the JVM exits. As per the java docs in OutputType.FILE (and other OutputTypes),
            //it is up to users to make a copy of this file. In this instance, I am not moving the file anywhere, i am
            //happy for it to be deleted

            assertThat(temporaryImageFile.length(), is(greaterThan(0L)));

            //Use these below lines in debug mode (to find out where the file is - remember if i want to store it somewhere,
            //I have to move the file myself

            System.out.println("Temp file written to : " + temporaryImageFile.getAbsolutePath());
            //i can also get the driver to display the screenshot in the browser - as below...
            driver.get("File://" + temporaryImageFile.getAbsolutePath());

        } else {
            fail("Driver did not support screenshots");
        }
    }

    @Test
    public void canWeTakeAScreenshotCapabilitiesStyleHtmlUnitDriver() {

        //We would expect an HtmlUnitDriver to NOT be able to take screenshots

        driver = new HtmlUnitDriver();
        driver.get("http://seleniumsimplified.com");

        HasCapabilities capabilityDriver = (HasCapabilities) driver;

        if(capabilityDriver.getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)) {
            fail("Expected HtmlUnitDriver to report false for takes screenshot");
        }
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
