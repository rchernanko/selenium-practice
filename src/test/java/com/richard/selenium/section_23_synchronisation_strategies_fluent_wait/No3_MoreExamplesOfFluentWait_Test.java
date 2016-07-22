package com.richard.selenium.section_23_synchronisation_strategies_fluent_wait;

import com.google.common.base.Function;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class No3_MoreExamplesOfFluentWait_Test {

    public WebDriver driver = new ChromeDriver();

    @Test
    public void fluentWaitExampleUsingExpectedConditions() {

        driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(10, TimeUnit.SECONDS). //in a webdriverwait, these are covered by defaults (and in constructor)
                pollingEvery(100, TimeUnit.MILLISECONDS). //in a webdriverwait, these are covered by defaults (and in constructor)
                ignoring(NotFoundException.class);

        wait.until(titleIs("HTML Form Elements"));

        assertEquals("HTML Form Elements", driver.getTitle());
    }

    /*

    This next method uses WebDriverWait instead of FluentWait
    It is the equivalent of the above

     */

    @Test
    public void webDriverWaitExampleUsingExpectedConditions() {

        driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10).
                pollingEvery(100, TimeUnit.MILLISECONDS);

        wait.ignoring(NotFoundException.class);
        //don't really need to do above as it's already part of WebDriverWait (see JavaDocs for more info)

        wait.until(titleIs("HTML Form Elements"));

        assertEquals("HTML Form Elements", driver.getTitle());
    }

    /*

    Below is an example of FluentWait that does not use WebDriver at all...it uses a Long
    Remember, a FluentWait allows us to wait on anything...very flexible class!

     */

    @Test
    public void fluentWaitExampleThatDoesNotUseWebDriver() {

        //This test will pass when the currentTime (See below) comes 4 seconds AFTER the startTime

        Long startTime = System.currentTimeMillis();

        FluentWait<Long> wait = new FluentWait<Long>(startTime).
                withTimeout(7, TimeUnit.SECONDS).
                pollingEvery(50, TimeUnit.MILLISECONDS).
                withMessage("You timed out!!!!");

        Long endTime = wait.until(new Function<Long, Long>() {

            @Override
            public Long apply(Long startTime) {

                Long currentTime = System.currentTimeMillis();

                if(currentTime > (startTime + 4000)) {
                    return currentTime;
                } else {
                    return null;
                }
            }
        });

        System.out.println("Actual Time difference = " + (endTime - startTime) + " milliseconds");
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
