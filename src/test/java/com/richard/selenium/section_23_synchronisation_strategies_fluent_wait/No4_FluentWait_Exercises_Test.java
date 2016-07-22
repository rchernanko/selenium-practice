package com.richard.selenium.section_23_synchronisation_strategies_fluent_wait;

import com.google.common.base.Function;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class No4_FluentWait_Exercises_Test {

    /*

    So he task:

    - Using "javascript_countdown.html"...

        - Create a fluent wait that returns a String with the time when the last 2 chars are "04"
            - Assert the returned time is 01:01:04

        - Do the same with a WebDriverWait

     */

    private static final String BASE_URL = "http://compendiumdev.co.uk/selenium/";
    private WebDriver driver;
    private WebElement countdown;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.navigate().to(BASE_URL + "javascript_countdown.html");
        countdown = driver.findElement(By.id("javascript_countdown_time"));
        new WebDriverWait(driver, 10).until(visibilityOf(countdown));
    }

    @Test
    public void fluentWaitExerciseWithWebDriver() {

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).

                withTimeout(13, TimeUnit.SECONDS).
                pollingEvery(100, TimeUnit.MILLISECONDS).
                withMessage("04 seconds has not been seen!").
                ignoring(NotFoundException.class);

        String currentTime = wait.until(new Function<WebDriver, String>() {

            @Override
            public String apply(WebDriver time) {

                String currentTime = driver.findElement(By.id("javascript_countdown_time")).getText();

                if (currentTime.endsWith("04")) {
                    return currentTime;
                } else {
                    return null;
                }
            }
        });

        assertThat("Expected a different time", currentTime, is("01:01:04"));
    }

    @Test
    public void fluentWaitExerciseWithWebElement() {

        String theTime = new FluentWait<WebElement>(countdown).

                withTimeout(13, TimeUnit.SECONDS).
                pollingEvery(100, TimeUnit.MILLISECONDS).
                withMessage("04 seconds has not been seen!").

                until(new Function<WebElement, String>() {
                    @Override
                    public String apply(WebElement element) {
                        return element.getText().endsWith("04") ? element.getText() : null;
                    }
                });

        assertThat("Expected a different time", theTime, is("01:01:04"));

    }

    @Test
    public void sameAsAboveButWithWebDriverWait() {

        String theTime = new WebDriverWait(driver, 13, 100).
                until(new ExpectedCondition<String>() {
                    @Override
                    public String apply(WebDriver driver) {
                        return countdown.getText().endsWith("04") ? countdown.getText() : null;
                    }
                });

        assertThat("Expected a different time", theTime, is("01:01:04"));
    }

    //The thing I've realised is that there are about 1000 ways to achieve the same thing when it comes to
    //expected conditions and fluent waits etc

    @After
    public void quitDriver() {
        driver.quit();
    }
}
