package com.richard.selenium.section_31_taking_screenshots;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class No1_Introduction {

    /*

    See readme for introduction image - there are a couple of different ways to take a screenshot...
    (check for capabilities or cast in a try/catch block)

    Here's a very simple example of taking screenshots...

     */

    private WebDriver driver;

    @Test
    public void goToPage()
            throws IOException {

        //Let's create a very simple test

        driver = new ChromeDriver();
        driver.get("http://seleniumsimplified.com");

        //We have to cast the driver to a TakesScreenshot object
        TakesScreenshot snapper = (TakesScreenshot) driver;

        //the screenshot is saved as a "FILE" into a temporary location
        File tempScreenshot = snapper.getScreenshotAs(OutputType.FILE);

        //the screenshot is saved as a "FILE" into a temporary location (which i can then see here)
        System.out.println(tempScreenshot.getAbsolutePath());

        //here is where i want to store the file eventually
        String pathDirectory = System.getProperty("user.dir") + "/screenshots";

        File myScreenshot = new File(pathDirectory, "goToPageScreen.png");

        //i have to move the file from its temporary location into the new one :-)
        FileUtils.moveFile(tempScreenshot, myScreenshot);

        assertThat(myScreenshot.length(), is(greaterThan(0L)));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

}
