package com.richard.selenium.section_15_manipulation_select_helper_class;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import javax.sql.rowset.spi.SyncResolver;
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
    private static final String MULTI_SELECT_AREA = "[multiple=\"multiple\"]";
    private static final String DROPDOWN_AREA = "[name=\"dropdown\"]";

    @Before
    public void instantiateDriverAndVisitTestPage() {
        driver = new ChromeDriver();
        driver.get(BASE_URL + "/selenium/basic_html_form.html");
    }

    //1) Submit form and assert page title changes
    @Test
    public void submitFormAndAssertPageTitleChanges() {
//        String pageTitleBeforeClickingSubmit = driver.getTitle();
        WebElement multiSelectArea = driver.findElement(By.cssSelector(MULTI_SELECT_AREA));
        Select selectedWebElement = new Select(multiSelectArea);
        selectedWebElement.selectByVisibleText("Selection Item 4");

        WebElement dropdownArea = driver.findElement(By.cssSelector(DROPDOWN_AREA));
        Select selectedWebElement2 = new Select(dropdownArea);
//        selectedWebElement2.selectByVisibleText("Drop Down Item 4"); //won't work
        selectedWebElement2.selectByValue("dd4");

//
//        submitForm();
//        waitUntilProcessedPageLoads();
//        Assert.assertNotEquals("Page title has not changed", pageTitleBeforeClickingSubmit, driver.getTitle());
    }
}
