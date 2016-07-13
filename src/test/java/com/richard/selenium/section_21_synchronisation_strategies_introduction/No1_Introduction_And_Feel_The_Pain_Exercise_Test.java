package com.richard.selenium.section_21_synchronisation_strategies_introduction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class No1_Introduction_And_Feel_The_Pain_Exercise_Test {

    /*

    Synchronisation is one of the most important things we have to get a handle on when we are doing test automation

    Poor synchronisation leads to...

    - Test intermittency - sometimes it works locally, but not on e.g. CI
    - Slow test execution - you might have too many implicit waits - you're not synchronising on the right things!
    - False positives / negatives
    - Moaning!

    In this section, we will look at ways to handle synchronisation

    So the exercise "feel the pain" is as follows:

    Write a test which...

    - On http://www.compendiumdev.co.uk/selenium/basic_ajax.html
    - Select "Sever" from "combo1"
    - Select "Java" from "combo2"
    - Click "Code In It" button
    - Assert that the loaded page has "_valuelanguage_id" text of 23

    This should fail when running, but when you in debug and step over, the test should pass.....

    Mmmmm...we need to add some synchronisation!!!

     */

    private WebDriver driver;
    private static final String MULTI_SELECT_CATEGORY = "select#combo1";
    private static final String MULTI_SELECT_LANGUAGE = "select#combo2";
    private static final String CODE_IN_IT_BUTTON = "input[name=\"submitbutton\"]";
    private static final String LANGUAGE_VALUE_ID_ON_PROCESSED_PAGE = "#_valuelanguage_id";

    @Before
    public void instantiateDriver() {
        driver = new ChromeDriver();
    }

    @Test
    public void feelThePainExercise() {

        driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");

        //First of all, select server from combo1
        WebElement multipleSelectCategoryElement = driver.findElement(By.cssSelector(MULTI_SELECT_CATEGORY));
        Select multipleSelectCategory = new Select(multipleSelectCategoryElement);
        multipleSelectCategory.selectByValue("3");

        //Then select java from combo2
        WebElement multipleSelectLanguageElement = driver.findElement(By.cssSelector(MULTI_SELECT_LANGUAGE));
        Select multipleSelectLanguage = new Select(multipleSelectLanguageElement);
        multipleSelectLanguage.selectByValue("23");

        //Then click the code in it button
        driver.findElement(By.cssSelector(CODE_IN_IT_BUTTON)).click();

        //Assert that the loaded page has correct value
        String languageId = driver.findElement(By.cssSelector(LANGUAGE_VALUE_ID_ON_PROCESSED_PAGE)).getText();
        assertEquals("Language id is not correct", languageId, "23");
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
