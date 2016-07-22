package com.richard.selenium.section_24_synchronisation_strategies_implicit_vs_explicit_waits;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class No2_ImplicitWait_Example_Test {

    /*

    This is not ideal - we should really always look to use explicit waits...

    But for now, here is a quick example.

    This is actually the same test as "No2_Fix_The_Feel_The_Pain_Exercise_Test" - relates to Ajax requests being fired
    on the page. Whereas in that class, we used explicit waits (in the form of WebDriverWaits), here we are simply
    using implicit waits - see the before hook...

    The implicit wait has handled the ajax requests / page loads...

    But let's think - if the test is failing (after 10 seconds), we first have to try to understand where the failure
    point is (perhaps the ajax request is taking longer than 10 seconds), and then our only option (in present implementation)
    is to increase the implicit timeout...that will have a knock on effect on everything else (e.g. findElement) - tests
    will take longer to run, you'll get feedback slower. and the value of these tests will slowly decrease...

     */

    private WebDriver driver;
    private static final String MULTI_SELECT_CATEGORY = "select#combo1";
    private static final String MULTI_SELECT_LANGUAGE = "select#combo2";
    private static final String CODE_IN_IT_BUTTON = "input[name=\"submitbutton\"]";
    private static final String LANGUAGE_VALUE_ID_ON_PROCESSED_PAGE = "#_valuelanguage_id";

    @Before
    public void instantiateDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void implicitWaitExample() {

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