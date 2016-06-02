package com.richard.selenium.section_13_manipulation_of_web_elements;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class No2_Introducing_Webdriver_WaitTest {

    /*

    WebDriver has a helper class, WebDriverWait, which can help us synchronise our tests

    WebDriverWait is used in conjunction with another helper class, ExpectedConditions

    We can write simple synchronisation statements, such as:

    new WebDriverWait(driver, 10).
        until(ExpectedConditions.titleIs("HTML Form Elements"));

    The above creates a new WebDriverWait object, and basically says "I want you to wait for a maximum of 10 seconds until
    the condition is met"...

    If the condition is met before 10 seconds, the execution will simply move on.
    But if the condition is not met after 10 seconds, it will throw an exception

     */

    private WebDriver driver;
    private static final String BASE_URL = "http://compendiumdev.co.uk/selenium";

    @Before
    public void instantiateWebDriverInstance() {
        driver = new ChromeDriver();
    }

    @Test
    public void exampleUsingExpectedConditions() {

        driver.navigate().to(BASE_URL + "/basic_html_form.html");

        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("HTML Form Elements"));

        //TODO play around with the ExpectedConditions methods

        Assert.assertEquals("Title is not correct", "HTML Form Elements", driver.getTitle());
    }

    @After
    public void quitDriverInstance() {
        driver.quit();
    }
}
