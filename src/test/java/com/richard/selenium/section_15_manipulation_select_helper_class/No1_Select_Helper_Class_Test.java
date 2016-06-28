package com.richard.selenium.section_15_manipulation_select_helper_class;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class No1_Select_Helper_Class_Test {

    /*

    Another really useful Selenium support class is 'Select'. This makes working with 'select' elements a little easier.

    What's a select element? Well if I inspect element on the multiselect / dropdown
    on http://compendiumdev.co.uk/selenium/basic_html_form.html...

    ...I will see something like this in the HTML - <select name=""dropdown"> AND <select multiple="multiple">

    The way to use Select is you instantiate a new Select object passing in a WebElement in to its constructor:

        new Select (WebElement)

    And then there are a load of helper methods to then use, including:

        - .isMultiple()
        - .getOptions()
        - .getAllSelectedOptions()
        - .getFirstSelectedOption()
        - .selectByVisibleText()
        - .deselectAll()
        - etc etc //TODO have a look at the select class and maybe do some JUnit tests to get to know the methods better

    The exercise - Try repeating the previous select 1, 2 and 3 exercises (in Section 14) using Select helper class

    1) Submit form and assert page title changes
    2) Clear, then type comments, submit form and check output
    3) Submit form with radio 2 selected

     */

    private WebDriver driver;
    private static final String BASE_URL = "http://compendiumdev.co.uk";
    private static final String MULTI_SELECT_AREA = "select[multiple=\"multiple\"]";
    private static final String SUBMIT_BUTTON = "input[type=\"submit\"]";

    @Before
    public void instantiateDriverAndVisitTestPage() {
        driver = new ChromeDriver();
        driver.get(BASE_URL + "/selenium/basic_html_form.html");
    }

    @Test
    public void submitFormAndAssertPageTitleChanges() {

        WebElement multipleSelectElement = driver.findElement(By.cssSelector(MULTI_SELECT_AREA));

        Select multipleSelect = new Select(multipleSelectElement);
        Assert.assertTrue(multipleSelect.isMultiple());

        List<WebElement> selectedElements = multipleSelect.getAllSelectedOptions();
        Assert.assertEquals("By default expected only 1 selected", 1, selectedElements.size());
        Assert.assertEquals("By default expected different item", "Selection Item 4", selectedElements.get(0).getText().trim());

        multipleSelect.deselectAll();

        selectedElements = multipleSelect.getAllSelectedOptions();
        Assert.assertEquals("Expected cleared", 0, selectedElements.size());

        multipleSelect.selectByVisibleText("Selection Item 1");
        multipleSelect.selectByIndex(1);
        multipleSelect.selectByValue("ms3"); //after executing these 3 lines, 3 items will be selected

        selectedElements = multipleSelect.getAllSelectedOptions();
        Assert.assertEquals("Expected 3 selected", 3, selectedElements.size());

        submitForm();
        waitUntilProcessedPageLoads();

        /*

        The below was me just messing around with the Select APIs...

        WebElement multiSelectArea = driver.findElement(By.cssSelector(MULTI_SELECT_AREA));
        Select selectedMultiSelectArea = new Select(multiSelectArea);
        selectedMultiSelectArea.deselectAll();
        selectedMultiSelectArea.selectByVisibleText("Selection Item 4");
        selectedMultiSelectArea.getOptions().get(1).click();

         */
    }

    private void waitUntilProcessedPageLoads() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    private void submitForm() {
        driver.findElement(By.cssSelector(SUBMIT_BUTTON)).submit();
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
