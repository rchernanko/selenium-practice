package com.richard.selenium.section_14_manipulation_continued;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    private static final String CHECKBOX_1 = "input[value=\"cb1\"]";
    private static final String CHECKBOX_3 = "input[value=\"cb3\"]";
    private static final String DROPDOWN_OPTION_5 = "tr [value=\"dd5\"]";
    private static final String COMMENTS_AREA_ON_PROCESSED_PAGE = "#_comments #_valuecomments";
    private static final String RADIO_BUTTON_2_ON_PROCESSED_PAGE = "#_radioval #_valueradioval";
    private static final String CHECKBOX_AREA_ON_PROCESSED_PAGE = "#_checkboxes #_valuecheckboxes0";
    private static final String DROPDOWN_AREA_ON_PROCESSED_PAGE = "#_dropdown #_valuedropdown";

    @Before
    public void instantiateDriverAndVisitTestPage() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL + "/selenium/basic_html_form.html");
    }

    //1) Submit form and assert page title changes
    @Test
    public void submitFormAndAssertPageTitleChanges() {
        String pageTitleBeforeClickingSubmit = driver.getTitle();
        driver.findElement(By.cssSelector(SUBMIT_BUTTON)).submit();

        /*

        I initially used .click() on the above, but I have since changed it to .submit()...

        So what's the difference? From stack overflow...

        "
        Once you’ve finished filling out the form, you probably want to submit it. One way to do this would be to
        find the “submit” button and click it:

        driver.findElement(By.id("submit")).click();

        Alternatively, WebDriver has the convenience method “submit” on every element. If you call this on an
        element within a form, WebDriver will walk up the DOM until it finds the enclosing form and then calls
        submit on that. If the element isn’t in a form, then the NoSuchElementException will be thrown:

        element.submit();
        "

         */

        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));

        Assert.assertNotEquals("Page title has not changed", pageTitleBeforeClickingSubmit, driver.getTitle());
    }

    //FYI - I can also submit the form (i.e. the above test) with a keyboard press (to simulate tabbing)...
    @Test
    public void submitFormWithKeyPress() {
        //Won't work with Chrome browser though

        WebElement submitButton = driver.findElement(By.cssSelector(SUBMIT_BUTTON));
        submitButton.sendKeys(Keys.ENTER); //TODO look at the KEYS in more detail

        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));

        Assert.assertEquals("Expected the form to be processed", "Processed Form Details", driver.getTitle());
    }

    //2) Clear, then type comments, submit form and check output
    @Test
    public void clearFormTypeCommentsSubmitFormCheckOutput() {
        WebElement commentArea = driver.findElement(By.cssSelector(COMMENTS_TEXT_AREA));
        commentArea.clear();
        String commentToSend = "Hello there, this is a test";
        commentArea.sendKeys(commentToSend);
        driver.findElement(By.cssSelector(SUBMIT_BUTTON)).submit();

        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));

        Assert.assertEquals("Comments are not correct", commentToSend, driver.findElement(By.cssSelector(COMMENTS_AREA_ON_PROCESSED_PAGE)).getText());
    }

    //3) Submit form with radio 2 selected
    @Test
    public void submitFormWithRadio2Selected() {
        WebElement radioButton2 = driver.findElement(By.cssSelector(RADIO_BUTTON_2));

        //radioButton2 is not selected by default but we trust nothing...

        if(!radioButton2.isSelected()) {
            radioButton2.click();
        }

        driver.findElement(By.cssSelector(SUBMIT_BUTTON)).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));

        String radioButton2TextOnProcessedPage = driver.findElement(By.cssSelector(RADIO_BUTTON_2_ON_PROCESSED_PAGE)).getText();
        Assert.assertEquals("Radio button 2 is not present on the processed page", "rd2", radioButton2TextOnProcessedPage);
    }

    //4) Submit form with only checkbox 1 selected
    @Test
    public void submitFormWithOnly1CheckboxSelected() {
        WebElement checkbox3 = driver.findElement(By.cssSelector(CHECKBOX_3));

        if(checkbox3.isSelected()) {
            checkbox3.click();
        }

        WebElement checkbox1 = driver.findElement(By.cssSelector(CHECKBOX_1));

        if(!checkbox1.isSelected()) {
            checkbox1.click();
        }

        Assert.assertTrue("Checkbox 1 has not been selected", checkbox1.isSelected());
        driver.findElement(By.cssSelector(SUBMIT_BUTTON)).submit();

        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));

        Assert.assertTrue("Checkbox 1 details are not present on the processed page",
                driver.findElement(By.cssSelector(CHECKBOX_AREA_ON_PROCESSED_PAGE)).getText().equals("cb1"));
    }

    //TODO abstract submit button method

    //5) Submit form with drop down item 5 selected
    @Test
    public void submitFormWithDropDownItem5Selected() {
        WebElement dropDownItem5 = driver.findElement(By.cssSelector(DROPDOWN_OPTION_5));
        dropDownItem5.click();
        driver.findElement(By.cssSelector(SUBMIT_BUTTON)).submit();

        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));

        Assert.assertTrue("Dropdown 5 details are not present on the processed page",
                driver.findElement(By.cssSelector(DROPDOWN_AREA_ON_PROCESSED_PAGE)).getText().equals("dd5"));
    }

    //6) Submit form with multiple select 1, 2 & 3
    @Test
    public void submitFormWithMultipleSelectValues() {
        //TODO - might have to watch the video / do some further research here
    }

    //7 For bonus points, submit with a file, and check name on output
    @Test
    public void submitWithAFile() {
        //TODO - think you can send the file with a send keys but try this out
    }

    @After
    public void quitDriverInstance() {
        driver.quit();
    }
}
