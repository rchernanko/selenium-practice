package com.richard.selenium.section_28_different_browsers;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class No5_InternetExplorerDriver_Examples_Test {

    /*

    Of course, this is not going to work on my mac...

    See the readme for information on how to download the driver etc...

     */

    @BeforeClass
    public static void setUpTheIEDriverSystemProperty() {

        //TODO this will FAIL because I am writing this on a mac and have not download the IE Driver exe

        String currentUserDirectory = System.getProperty("user.dir");
        String IEDriverLocation = currentUserDirectory + "PATH TO DRIVER HERE";
        System.setProperty("webdriver.ie.driver", IEDriverLocation);
    }

    @Test
    public void basicIEDriverTest() {

        WebDriver driver = new InternetExplorerDriver();

        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");

        assertThat(driver.getTitle(), is("HTML Form Elements"));

        driver.quit();
    }
}