package com.richard.selenium.section_18_frames_and_windows;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class No1_Introduction_Test {

    /*

    Frames (see image in readme for more info). Several ways to get to frames:

    driver.switchTo().
        - frame ("framename")
        - frame(int)
        - frame(WebElement)
        - defaultContent() - takes us back to the top of the main page

    When you switch (as above), your commands act on the new <body>

    An example below...

    */

    private WebDriver driver = new ChromeDriver();

    @Test
    public void switchToFrameExample() {
        driver.get("http://www.compendiumdev.co.uk/selenium/frames");

        assertEquals("Frameset Example Title (Example 6)", driver.getTitle());

        driver.switchTo().frame("menu");

        driver.findElement(By.cssSelector("a[href=\"frames_example_1.html\"]")).click();

        String titleForExample1 = "Frameset Example Title (Example 1)";

        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs(titleForExample1));

        assertEquals(titleForExample1, driver.getTitle());
    }

}
