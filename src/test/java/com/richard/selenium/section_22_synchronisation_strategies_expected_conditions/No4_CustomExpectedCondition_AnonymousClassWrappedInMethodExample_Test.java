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

public class No4_CustomExpectedCondition_AnonymousClassWrappedInMethodExample_Test {

    /*

    So, let's create a Custom ExpectedCondition :-)

    3) The third way to do this is using an anonymous class wrapped within a method

    */

    private WebDriver driver;

    @Before
    public void createDriverAndOpenPage() {
        driver = new ChromeDriver();
        driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    @Test
    public void anonymousClassWrappedWithinAMethodExample() {

        //select server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value=\"3\"]")).click();

        WebElement javaOption = new WebDriverWait(driver, 10, 50).until(optionWithValueDisplayed("23"));
        javaOption.click();

        //submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        assertEquals("Expected Java code", "23", languageWeUsed.getText());
    }

    /*

    So here we've got an anonymous class wrapped in a method which takes a variable called value as a parameter
    In the body of the anonymous class, we are then using the "value" argument that's been passed in
    The variable has to be declared as final in order for it to be used in the anonymous class

     */

    private ExpectedCondition<WebElement> optionWithValueDisplayed(final String value) {
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.cssSelector("option[value=\"" + value + "\"]"));
            }
        };
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
