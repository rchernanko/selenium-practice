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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class No5_Exercise_Test {

    /*

    So on http://compendiumdev.co.uk/selenium/basic_ajax.html...

    - Create a WebDriverWait with an inline ExpectedCondition that returns a WebElement not a Boolean. Then assert on
    the text of that WebElement - e.g. Java from select dropdown

    - Use http://compendiumdev.co.uk/selenium/basic_redirect.html and create a custom ExpectedCondition:
        - for titleDoesNotContain(<String>)

        TODO - I definitely need to spend more time going over these and practising writing my own...

     */

    private WebDriver driver;

    @Before
    public void createDriver() {
        driver = new ChromeDriver();
    }

    @Test
    public void firstExercise() {

        //Uses an inline ExpectedCondition within the actual test

        driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");

        //select server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value=\"3\"]")).click();

        WebElement javaOption = new WebDriverWait(driver, 10).until(

                new ExpectedCondition<WebElement>() {

                    @Override
                    public WebElement apply(WebDriver driver) {

                        WebElement eli = driver.findElement(
                                By.cssSelector("option[value=\"23\"]"));

                        if (eli.isDisplayed()) {
                            return eli;
                        } else {
                            return null;
                        }
                    }
                });

        javaOption.click();
        assertThat("Element text is not correct", javaOption.getText(), is("Java"));
    }

    @Test
    public void secondExerciseExampleOne() {

        //Uses a named class for the expected condition

        driver.get("http://compendiumdev.co.uk/selenium/basic_redirect.html");

        //Click on element
        driver.findElement(By.cssSelector("#delaygotobounce")).click();

        assertEquals("Bounce", new WebDriverWait(driver, 4).until(new TitleDoesNotContain("Redirects")));
    }

    @Test
    public void secondExerciseExampleTwo() {

        //Uses a method to get back the ExpectedCondition

        WebDriverWait wait = new WebDriverWait(driver, 4);
        driver.get("http://compendiumdev.co.uk/selenium/basic_redirect.html");

        //Click on element
        driver.findElement(By.id("delaygotobounce")).click();

        assertEquals("Bounce", wait.until(titleDoesNotContain("Redirects")));
    }

    private ExpectedCondition<String> titleDoesNotContain(String stringNotInTitle) {
        return new TitleDoesNotContain(stringNotInTitle);
    }

    @Test
    public void secondExerciseExampleThree() {

        //The ExpectedCondition here is wrapped in a method. I think I actually prefer this one out of every other option.
        //Is a little easier to read and maintain IMO.

        WebDriverWait wait = new WebDriverWait(driver, 4);
        driver.get("http://compendiumdev.co.uk/selenium/basic_redirect.html");

        //Click on element
        driver.findElement(By.id("delaygotobounce")).click();

        assertEquals("Bounce", wait.until(titleDoesNotContainAC("Redirects")));
    }

    private ExpectedCondition<String> titleDoesNotContainAC(final String stringNotInTitle) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {

                String title = driver.getTitle();

                if(title.contains(stringNotInTitle)) {
                    return null;
                } else {
                    return title;
                }
            }
        };
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}

class TitleDoesNotContain implements ExpectedCondition<String> {

    private String titleExcludes;

    public TitleDoesNotContain(String notContainThisString) {
        this.titleExcludes = notContainThisString;
    }

    @Override
    public String apply(WebDriver driver) {

        String title = driver.getTitle();

        if(title.contains(this.titleExcludes)) {
            return null;
        } else {
            return title;
        }
    }
}




