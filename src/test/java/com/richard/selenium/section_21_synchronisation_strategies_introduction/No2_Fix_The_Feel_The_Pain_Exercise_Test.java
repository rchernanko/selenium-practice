package com.richard.selenium.section_21_synchronisation_strategies_introduction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class No2_Fix_The_Feel_The_Pain_Exercise_Test {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String MULTI_SELECT_CATEGORY = "select#combo1";
    private static final String MULTI_SELECT_LANGUAGE = "select#combo2";
    private static final String MULTI_SELECT_LANGUAGE_OPTIONS = "select#combo2 option";
    private static final String CODE_IN_IT_BUTTON = "input[name=\"submitbutton\"]";
    private static final String LANGUAGE_VALUE_ID_ON_PROCESSED_PAGE = "#_valuelanguage_id";
    private static final String PROCESSED_PAGE_HEADING ="body p:first-child";

    @Before
    public void instantiateDriver() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10, 50);
    }

    @Test
    public void feelThePainExerciseUsingWebDriverWait() {

        driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");

        //First of all, select server from combo1
        WebElement multipleSelectCategoryElement = driver.findElement(By.cssSelector(MULTI_SELECT_CATEGORY));
        Select multipleSelectCategory = new Select(multipleSelectCategoryElement);
        multipleSelectCategory.selectByValue("3");
        wait.until(textToBe(By.cssSelector(MULTI_SELECT_LANGUAGE_OPTIONS), "Cobol"));

        //In the above, I have the expected conditions static method 'textToBe' as a static import - reads nicer :-)

        //Other ways to handle above using expected conditions include...
        wait.until(textToBePresentInElementLocated(By.cssSelector(MULTI_SELECT_LANGUAGE_OPTIONS), "Cobol"));
        wait.until(attributeToBe(driver.findElement(By.cssSelector(MULTI_SELECT_LANGUAGE_OPTIONS)), "value", "20"));
        wait.until(presenceOfElementLocated(By.cssSelector("option[value=\"23\"]")));
        wait.until(visibilityOfElementLocated(By.cssSelector("option[value=\"23\"]")));
        wait.until(elementToBeClickable(By.cssSelector("option[value=\"23\"]")));


        //Then select java from combo2
        WebElement multipleSelectLanguageElement = driver.findElement(By.cssSelector(MULTI_SELECT_LANGUAGE));
        Select multipleSelectLanguage = new Select(multipleSelectLanguageElement);
        multipleSelectLanguage.selectByValue("23");

        //Then click the code in it button
        driver.findElement(By.cssSelector(CODE_IN_IT_BUTTON)).click();

        //Now I am going to add a wait condition to handle the new page load...
        wait.until(titleIs("Processed Form Details"));

        //Another way to handle above using expected conditions is...
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(PROCESSED_PAGE_HEADING), "Submitted Values"));

        //Assert that the loaded page has correct value
        String languageId = driver.findElement(By.cssSelector(LANGUAGE_VALUE_ID_ON_PROCESSED_PAGE)).getText();
        assertEquals("Language id is not correct", languageId, "23");
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}