package com.richard.selenium.section_23_synchronisation_strategies_fluent_wait;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class No1_Introduction_Test {

    /*

    WebDriverWait is built on top of (i.e. extends) another class called FluentWait

    FluentWait provides us with some methods that allow us to construct a WebDriverWait "fluently" i.e. make it nicer to read.

    For example:

    - .pollingEvery - we can use this instead of adding the polling time in the WebDriverWait constructor

    - .ignoring - to ignore additional exceptions (we know that 'ElementNotFoundException' is ignored but this gives us
                  the option to ignore other exceptions

    - .withTimeout - we can use this instead of adding the timeout time in the WebDriverWait constructor

    - .withMessage - we can add a message that happens on timeout

    - .ignoreAll - we can ignore a bunch of exceptions

    An example below...

     */

    public WebDriver driver = new ChromeDriver();

    @Test
    public void useWebDriverWaitFluentlyExample() {

        driver.get("http://compendiumdev.co.uk");

        long currentTime = System.currentTimeMillis();

        try {

            //this will ignore the thrown exceptions in the apply...
            new WebDriverWait(driver, 1).

                    pollingEvery(100, TimeUnit.MILLISECONDS). //Override polling time
                    ignoring(IllegalStateException.class). //ignore exceptions
                    withTimeout(10, TimeUnit.SECONDS). //override timeout
                    withMessage("See I told you a timeout happened"). //include text in timeout message

                    until(
                    new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(WebDriver webDriver) {
                            throw new IllegalStateException();
                        }
                    });

            fail("A time out exception should have been thrown");

        } catch (TimeoutException e) {
            assertTrue(e.getMessage().contains("See I told you a timeout happened"));
        }

        long nowTime = System.currentTimeMillis();

        assertTrue((nowTime - currentTime) > 10000);

    }

    @After
    public void quitDriver() {
        driver.quit();
    }

}
