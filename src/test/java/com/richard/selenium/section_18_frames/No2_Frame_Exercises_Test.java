package com.richard.selenium.section_18_frames;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class No2_Frame_Exercises_Test {

    /*

    Few exercises:

    - Go to the "content" frame. Choose to "load green page". Then on "Green Page", choose to go back to the original page.
    When on the the original page, assert for the presence of "<h1>Content</h1>"

    - Click on the "menu" frame and then on "iFrames Example". Then in the iFrame, click on "Example 5". Then in the
     "content" frame, select "Load Main Frames Page"

     */

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "http://compendiumdev.co.uk/";


    @Before
    public void instantiateDriverAndGoToPage() {
        driver = new MarionetteDriver();
        driver.get(BASE_URL + "/selenium/frames/");
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void exercise1() {
        Assert.assertThat(driver.getTitle(), is("Frameset Example Title (Example 6)"));
        driver.switchTo().frame("content");
        Assert.assertThat("Driver has not switched to the correct frame", driver.findElement(By.tagName("h1")).getText(), is("Content"));
        driver.findElement(By.cssSelector("a[href=\"green.html\"]")).click();
        wait.until(visibilityOf(driver.findElement(By.cssSelector("h1[id=\"green\"]"))));
        driver.findElement(By.cssSelector("a[href=\"content.html\"]")).click();
        Assert.assertTrue((driver.findElement(By.tagName("h1")).isDisplayed()) && (driver.findElement(By.tagName("h1")).getText().equals("Content")));
    }

    @Test
    public void exercise2() {
        Assert.assertThat(driver.getTitle(), is("Frameset Example Title (Example 6)"));
        driver.switchTo().frame("menu");
        Assert.assertThat("Driver has not switched to the correct frame", driver.findElement(By.tagName("h3")).getText(), is("Menu 1"));
        driver.findElement(By.cssSelector("a[href=\"iframe.html\"]")).click();
        wait.until(titleIs("HTML Frames Example - iFrame Contents"));
        driver.switchTo().frame(driver.findElement(By.tagName("iFrame")));
        driver.findElement(By.cssSelector("a[href=\"frames_example_5.html\"]")).click();
        wait.until(titleIs("Frameset Example Title (Example 5)"));
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href=\"index.html\"")).click();
        Assert.assertEquals("Main frames page has not been loaded", "Frameset Example Title (Example 6)", driver.getTitle());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
