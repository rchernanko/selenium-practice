package com.richard.selenium.section_22_synchronisation_strategies_expected_conditions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class No3_CustomExpectedCondition_AnonymousClassExample_Test {

    /*

    So, let's create a Custom ExpectedCondition :-)

    2) The second way to do this is using an anonymous class - we will create an adhoc wait

    */

    private WebDriver driver;

    @Before
    public void createDriverAndGoToPage() {
        driver = new ChromeDriver();
        driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    @Test
    public void anonymousClassExample() {

        //select server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value=\"3\"]")).click();

        new WebDriverWait(driver, 10, 50).until(

                //Now I will create an ExpectedCondition on the fly and override the apply method.

                //So I am creating an anonymous class...but of course, anonymous classes cannot have custom constructors
                //So I can't pass in any additional information that I would want to use for my synchronisation (like I
                //did in the named class example)...

                //The only thing I can use is the instance of webdriver that is passed into the apply method

                //So they should only really be used in very simplistic cases - can't reuse it...one off

                //The way to get around this limitation is to either use a separate named class (example 1) or to wrap
                //the anonymous class within a method (see example 3)

                new ExpectedCondition<Boolean>() {

                    @Override
                    public Boolean apply(WebDriver driver) {

                        return driver.findElement(
                                By.cssSelector("option[value=\"23\"]")).isDisplayed();
                    }
                });

        //then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value=\"23\"]")).click();

        //submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        assertEquals("Expected Java code", "23", languageWeUsed.getText());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
