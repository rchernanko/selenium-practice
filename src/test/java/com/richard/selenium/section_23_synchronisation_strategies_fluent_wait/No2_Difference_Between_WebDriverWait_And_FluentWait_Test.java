package com.richard.selenium.section_23_synchronisation_strategies_fluent_wait;

import com.google.common.base.Function;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class No2_Difference_Between_WebDriverWait_And_FluentWait_Test {

    /*

    - WebDriverWait extends FluentWait so everything you see in No1_Introduction_Test is really FluentWait

    INTERESTING:

    The main difference between FluentWait and WebDriverWait:

        - FluentWait until can APPLY to ANYTHING e.g. WebElement, String, Time, Files etc + can return anything
            - it is a generic wait class

        - WebDriverWait until ONLY APPLIES to WEBDRIVER + can return anything

    See an example below of a FluentWait with WebElement...

     */

    public WebDriver driver;

    @Before
    public void createDriver() {
        driver = new ChromeDriver();
    }

    @Test
    public void fluentWaitWithWebElementExample() {

        WebElement countdown =  driver.findElement(By.id("javascript_countdown_time"));

        new FluentWait<WebElement> (countdown). //declare a WebElement will be passed to the wait + then pass it in

                withTimeout(10, TimeUnit.SECONDS). // configure wait fluently
                pollingEvery(100, TimeUnit.MILLISECONDS). //configure wait fluently

                until(new Function<WebElement, Boolean>() { //using a Function instead of an ExpectedCondition + declaring that this will return a Boolean

                    @Override
                    public Boolean apply(WebElement webElement) {
                        return webElement.getText().endsWith("04");
                    }
                });
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
