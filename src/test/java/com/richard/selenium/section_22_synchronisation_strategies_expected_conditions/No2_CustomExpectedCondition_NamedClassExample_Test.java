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

import java.util.List;

import static org.junit.Assert.assertEquals;

public class No2_CustomExpectedCondition_NamedClassExample_Test {

    /*

    So, let's create a Custom ExpectedCondition :-)

    1) The first way to do this is using a separate named class:

    So the first thing I will do is create a custom expected condition using a named class (SelectContainsText) - see
    below.

    Note - In this instance, the class (SelectContainsText) is private because it's going to be local to my test.
    If it were in a separate class of it's own, it would be public...

    So the first thing I do is "implement" ExpectedCondition and this must return something.

    The next thing I'll do is create a constructor for SelectContainsText - I will pass in the whatever i need in the
    constructor - in this instance a By and a String...

    Then, because I am implementing ExpectedCondition<Boolean>, I must then override the "apply" method (which is actually
    called by WebDriverWait.

    It is in this apply method that I implement my checking code using the passed in WebDriver...

     */

    private class SelectContainsText implements ExpectedCondition<Boolean> {

        private String textToFind;
        private By findBy;

        public SelectContainsText(final By comboFindBy, final String textToFind) {
            this.findBy = comboFindBy;
            this.textToFind = textToFind;
        }

        @Override
        public Boolean apply(WebDriver driver) {

            WebElement combo = driver.findElement(this.findBy);
            List<WebElement> options = combo.findElements(By.tagName("option"));

            for(WebElement anOption: options) {
                if(anOption.getText().equals(this.textToFind))
                    return true;
            }
            return false;
        }

        //Whenever you are creating your own classes, it's always good to add a toString() method
        @Override
        public String toString() {
            return "select" + this.findBy + " to contain " + this.textToFind;
        }
    }

    //And now for the actual test!

    private WebDriver driver;

    @Before
    public void createDriverAndGoToPage() {
        driver = new ChromeDriver();
        driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    @Test
    public void namedClassExample() {

        //select server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value=\"3\"]")).click();

        new WebDriverWait(driver, 10, 50).until
                ((new SelectContainsText(By.id("combo2"), "Java")));

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
