package com.richard.selenium.section_14_manipulation_continued;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
    private static final String MULTI_SELECT_AREA = "[multiple=\"multiple\"]";
    private static final String MULTI_SELECT_OPTIONS = "option";
    private static final String CHECKBOX_1 = "input[value=\"cb1\"]";
    private static final String CHECKBOX_2 = "input[value=\"cb2\"]";
    private static final String CHECKBOX_3 = "input[value=\"cb3\"]";
    private static final String CHOOSE_FILE_TO_UPLOAD_BUTTON = "input[type=\"file\"]";
    private static final String DROPDOWN_OPTION_5 = "tr [value=\"dd5\"]";
    private static final String COMMENTS_AREA_ON_PROCESSED_PAGE = "#_comments #_valuecomments";
    private static final String RADIO_BUTTON_2_ON_PROCESSED_PAGE = "#_radioval #_valueradioval";
    private static final String CHECKBOX_1_AREA_ON_PROCESSED_PAGE = "#_checkboxes #_valuecheckboxes0";
    private static final String CHECKBOX_2_AREA_ON_PROCESSED_PAGE = "#_checkboxes #_valuecheckboxes1";
    private static final String CHECKBOX_3_AREA_ON_PROCESSED_PAGE = "#_checkboxes #_valuecheckboxes2";
    private static final String DROPDOWN_AREA_ON_PROCESSED_PAGE = "#_dropdown #_valuedropdown";
    private static final String FILENAME_ON_PROCESSED_PAGE = "#_valuefilename";

    @Before
    public void instantiateDriverAndVisitTestPage() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL + "/selenium/basic_html_form.html");
    }

    //1) Submit form and assert page title changes
    @Test
    public void submitFormAndAssertPageTitleChanges() {
        String pageTitleBeforeClickingSubmit = driver.getTitle();
        submitForm();
        waitUntilProcessedPageLoads();
        Assert.assertNotEquals("Page title has not changed", pageTitleBeforeClickingSubmit, driver.getTitle());
    }

    //FYI - I can also submit the form (i.e. the above test) with a keyboard press (to simulate tabbing)...
    @Test
    public void submitFormWithKeyPress() {
        //Won't work with Chrome browser though

        WebElement submitButton = driver.findElement(By.cssSelector(SUBMIT_BUTTON));
        submitButton.sendKeys(Keys.ENTER); //TODO look at the KEYS in more detail

        waitUntilProcessedPageLoads();

        Assert.assertEquals("Expected the form to be processed", "Processed Form Details", driver.getTitle());
    }

    //2) Clear, then type comments, submit form and check output
    @Test
    public void clearFormTypeCommentsSubmitFormCheckOutput() {
        WebElement commentArea = driver.findElement(By.cssSelector(COMMENTS_TEXT_AREA));
        commentArea.clear();
        String commentToSend = "Hello there, this is a test";
        commentArea.sendKeys(commentToSend);
        submitForm();

        waitUntilProcessedPageLoads();

        Assert.assertEquals("Comments are not correct", commentToSend, driver.findElement(By.cssSelector(COMMENTS_AREA_ON_PROCESSED_PAGE)).getText());
    }

    //3) Submit form with radio 2 selected
    @Test
    public void submitFormWithRadio2Selected() {
        WebElement radioButton2 = driver.findElement(By.cssSelector(RADIO_BUTTON_2));

        //radioButton2 is not selected by default but we trust nothing...

        if (!radioButton2.isSelected()) {
            radioButton2.click();
        }

        submitForm();

        waitUntilProcessedPageLoads();

        String radioButton2TextOnProcessedPage = driver.findElement(By.cssSelector(RADIO_BUTTON_2_ON_PROCESSED_PAGE)).getText();
        Assert.assertEquals("Radio button 2 is not present on the processed page", "rd2", radioButton2TextOnProcessedPage);
    }

    //4) Submit form with only checkbox 1 selected
    @Test
    public void submitFormWithOnlyCheckbox1Selected() {
        WebElement checkbox3 = driver.findElement(By.cssSelector(CHECKBOX_3));

        //By default, checkbox 3 is selected - so we want to unselect it for the purpose of this test
        if (checkbox3.isSelected()) {
            checkbox3.click();
        }

        //To make the test robust, let's also check that checkbox 2 is not selected
        WebElement checkbox2 = driver.findElement(By.cssSelector(CHECKBOX_2));

        if(checkbox2.isSelected()) {
            checkbox2.click();
        }

        //Then we want to make sure that checkbox 1 IS SELECTED
        WebElement checkbox1 = driver.findElement(By.cssSelector(CHECKBOX_1));

        if (!checkbox1.isSelected()) {
            checkbox1.click();
        }

        Assert.assertTrue("Checkbox 1 has not been selected", checkbox1.isSelected());
        Assert.assertTrue("Checkbox 2 is selected and shouldn't be", !checkbox2.isSelected());
        Assert.assertTrue("Checkbox 3 is selected and shouldn't be", !checkbox3.isSelected());

        //I've abstracted the below into separate methods. Longer term, these would need to go into page
        //objects and refactor. To start with, we will just get the test up and running
        submitForm();
        waitUntilProcessedPageLoads();
        assertCheckboxResults();
    }

    //5) Submit form with drop down item 5 selected //TODO currently passing but see other todo comments below
    @Test
    public void submitFormWithDropDownItem5Selected() {
        WebElement dropDownItem5 = driver.findElement(By.cssSelector(DROPDOWN_OPTION_5));
        dropDownItem5.click();

        //TODO - check that other dropdown values are not selected

        submitForm();

        waitUntilProcessedPageLoads();

        Assert.assertTrue("Dropdown 5 details are not present on the processed page",
                driver.findElement(By.cssSelector(DROPDOWN_AREA_ON_PROCESSED_PAGE)).getText().equals("dd5"));
        //TODO amend this as this would not necessarily check whether other dropdowns have been selected. watch video again
    }

    //6) Submit form with multiple select 1, 2 & 3
    @Test
    public void submitFormWithMultipleSelectValues() {

        WebElement multiSelectArea = driver.findElement(By.cssSelector(MULTI_SELECT_AREA));
        List<WebElement> multiSelectOptions = multiSelectArea.findElements(By.tagName(MULTI_SELECT_OPTIONS));

        multiSelectOptions.get(0).click();
        multiSelectOptions.get(1).click();
        multiSelectOptions.get(2).click();

        /*

        It's really important here to understand that there is a slight nuance in web driver at this point (relating
        to the above)

        If (manually), a user firstly clicked on option 0, then on option 1 and then 2...ONLY option 2 would actually
        be end up being selected...

        However, web driver works differently and the above will actually ensure that options 0, 1 and 2 are ALL
        selected in the multi select area

        Is really important to be aware of this.

        Similarly, with the below. When you land on the form, before you've selected anything, the multiSelectOption 3
        is selected by default. Then, when you select another option (e.g. multiSelectOption 2), the default is cleared.
        However, when we use web driver, the default option is not cleared - hence why we have to un-select it below...

         */

        if (multiSelectOptions.get(3).isSelected()) {
            multiSelectOptions.get(3).click();
        }

        submitForm();
        waitUntilProcessedPageLoads();

        Assert.assertEquals("Multi-select value not correct on processed page", "ms1",
                driver.findElement(By.cssSelector("#_valuemultipleselect0")).getText());
        Assert.assertEquals("Multi-select value not correct on processed page", "ms2",
                driver.findElement(By.cssSelector("#_valuemultipleselect1")).getText());
        Assert.assertEquals("Multi-select value not correct on processed page", "ms3",
                driver.findElement(By.cssSelector("#_valuemultipleselect2")).getText());
        Assert.assertTrue("Multi-select value not correct on processed page",
                driver.findElements(By.cssSelector("#_valuemultipleselect3")).isEmpty());
    }

    //7 For bonus points, submit with a file, and check name on output
    @Test
    public void submitWithAFile() {

        WebElement chooseFileToUploadButton = driver.findElement(By.cssSelector(CHOOSE_FILE_TO_UPLOAD_BUTTON));
        chooseFileToUploadButton.sendKeys("/manipulation_test_file.txt");
        submitForm();
        waitUntilProcessedPageLoads();

        Assert.assertEquals("File has not been uploaded correctly", "manipulation_test_file.txt", driver.findElement(By.cssSelector(FILENAME_ON_PROCESSED_PAGE)).getText());
    }

    private void assertCheckboxResults() {
        WebElement checkboxResult1 = driver.findElement(By.cssSelector(CHECKBOX_1_AREA_ON_PROCESSED_PAGE));
        WebElement checkboxResult2 = null;
        WebElement checkboxResult3 = null;

        try {
            checkboxResult2 = driver.findElement(By.cssSelector(CHECKBOX_2_AREA_ON_PROCESSED_PAGE));
            checkboxResult3 = driver.findElement(By.cssSelector(CHECKBOX_3_AREA_ON_PROCESSED_PAGE));
        } catch (NoSuchElementException e) {
            // expected missing element
        }

        Assert.assertTrue("Checkbox 1 details are not present on the processed page",
                checkboxResult1.getText().equals("cb1"));
        Assert.assertTrue("Checkbox 2 details are present on the processed page and should not be",
                checkboxResult2 == null);
        Assert.assertTrue("Checkbox 3 details are present on the processed page and should not be",
                checkboxResult3 == null);
    }

    private void waitUntilProcessedPageLoads() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    private void submitForm() {
        driver.findElement(By.cssSelector(SUBMIT_BUTTON)).submit();
    }

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

    @After
    public void quitDriverInstance() {
        driver.quit();
    }
}
