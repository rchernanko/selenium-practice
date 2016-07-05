package com.richard.selenium.section_19_windows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class No1_Windows_Introduction_Test {

    /*

    Each browser window that webdriver is managing has a unique handle, e.g.:
    CDwindow-F4F902AE-809E-4E0A-8B31-2D7101D97C53

    The way we get these handles is at the driver level:

        - driver.getWindowHandle() returns handle for the current windows
        - driver.getWindowHandles() returns a Set<String> of all window handles currently available to me

    You can switch control between different windows:

        - driver.switchTo.window(String handle)


    An example below...

     */

    private WebDriver driver;

    @Before
    public void instantiateDriver() {
        driver = new ChromeDriver();
    }

    @Test
    public void switchToNewWindow() {
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/frames");

        //we start with one window open
        assertEquals("Expected only 1 current window", 1, driver.getWindowHandles().size());

        //remember the current window handle
        String framesWindowHandle = driver.getWindowHandle();

        driver.switchTo().frame("content");

        //clicking on this will open a new window
        driver.findElement(By.cssSelector("a[href=\"http://www.seleniumsimplified.com\"]")).click();

        assertEquals("Expected a new window opened", 2, driver.getWindowHandles().size());

        Set<String> myWindows = driver.getWindowHandles();
        String newWindowHandle = "";

        //find the new window handle
        for(String aHandle : myWindows) {
            if(!framesWindowHandle.contentEquals(aHandle)) {
                newWindowHandle = aHandle;
                break;
            }
        }

        //switch to the new window
        driver.switchTo().window(newWindowHandle);

        //driver commands now act on new window
        assertTrue("Expected Selenium Simplified site", driver.getTitle().contains("Selenium Simplified"));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
