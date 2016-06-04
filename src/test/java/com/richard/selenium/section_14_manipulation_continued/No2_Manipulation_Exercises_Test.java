package com.richard.selenium.section_14_manipulation_continued;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class No2_Manipulation_Exercises_Test {

    /*

    Exercises:

    Using http://compendiumdev.co.uk/selenium/basic_html_form.html...

    1) Submit form and assert page title changes
    2) Clear, then type comments, submit form and check output
    3) Submit form with radio 2 selected
    4) Submit form with only checkbox 1 selected
    5) Submit form with drop down item 5 selected
    6) Submit form with multiple select 1, 2 & 3
    7) For bonus points, submit with a file, and check name on output

     */

    private WebDriver driver;
    private static final String BASE_URL = "http://compendiumdev.co.uk";
    private static final String SUBMIT_BUTTON = "input[type=\"submit\"]";
    private static final String RADIO_BUTTON_2 = "input[value=\"rd2\"]";
    private static final String COMMENTS_TEXT_AREA = "textArea";
    private static final String CHECKBOX_1 = "";
    private static final String CHECKBOX_3 = "";
    private static final String COMMENTS_AREA_ON_PROCESSED_PAGE = "#_comments #_valuecomments";
    private static final String RADIO_BUTTON_2_ON_PROCESSED_PAGE = "#_radioval #_valueradioval";

    @Before
    public void instantiateDriverAndVisitTestPage() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL + "/selenium/basic_html_form.html");
    }

    //1) Submit form and assert page title changes
    @Test
    public void submitFormAndAssertPageTitleChanges() {
        String pageTitleBeforeClickingSubmit = driver.getTitle();
        driver.findElement(By.cssSelector(SUBMIT_BUTTON)).click();
        Assert.assertNotEquals("Page title has not changed", pageTitleBeforeClickingSubmit, driver.getTitle());
    }

    //2) Clear, then type comments, submit form and check output
    @Test
    public void clearFormTypeCommentsSubmitFormCheckOutput() {
        WebElement commentArea = driver.findElement(By.cssSelector(COMMENTS_TEXT_AREA));
        commentArea.clear();
        String commentToSend = "Hello there, this is a test";
        commentArea.sendKeys(commentToSend);
        driver.findElement(By.cssSelector(SUBMIT_BUTTON)).click();
        Assert.assertTrue("Comments are not correct", commentToSend.equals(driver.findElement(By.cssSelector(COMMENTS_AREA_ON_PROCESSED_PAGE)).getText()));
    }

    //3) Submit form with radio 2 selected
    @Test
    public void submitFormWithRadio2Selected() {
        WebElement radioButton2 = driver.findElement(By.cssSelector(RADIO_BUTTON_2));
        radioButton2.click();
        Assert.assertTrue("Radio button 2 is not selected", radioButton2.isSelected());
        driver.findElement(By.cssSelector(SUBMIT_BUTTON)).click();
        String radioButton2TextOnProcessedPage = driver.findElement(By.cssSelector(RADIO_BUTTON_2_ON_PROCESSED_PAGE)).getText();
        Assert.assertTrue("Radio button 2 is not present on the processed page", radioButton2TextOnProcessedPage.equals("rd2"));
    }

    //4) Submit form with only checkbox 1 selected
    @Test
    public void submitFormWithOnly1CheckboxSelected() {
//        driver.findElement(By.cssSelector(CHECKBOX_3)).click();
        //TODO assert unselected
//        driver.findElement(By.cssSelector(CHECKBOX_1)).click();
        //TODO Assert selected
        //TODO submit
    }

    @After
    public void quitDriverInstance() {
        driver.quit();
    }
}
