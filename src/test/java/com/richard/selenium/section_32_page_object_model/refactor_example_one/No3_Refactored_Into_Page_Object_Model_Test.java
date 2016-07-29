package com.richard.selenium.section_32_page_object_model.refactor_example_one;

import com.richard.selenium.section_32_page_object_model.refactor_example_one.pages.BasicAjaxPageObject;
import com.richard.selenium.section_32_page_object_model.refactor_example_one.pages.ProcessedFormPageObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.richard.selenium.section_32_page_object_model.refactor_example_one.pages.BasicAjaxPageObject.Category;
import static com.richard.selenium.section_32_page_object_model.refactor_example_one.pages.BasicAjaxPageObject.Language;
import static org.junit.Assert.assertEquals;

public class No3_Refactored_Into_Page_Object_Model_Test {

    /*

    This class has been refactored into a page object model, complete with some nice abstraction.
    To see the original class (which has driver.gets within the test etc), please look at
    "No2_TestClass_To_Be_Refactored".

    To recap what I've done here, simply re-watch the video :-) ALSO there is a summary at the bottom of this class :-)

    The enums are fantastic :-)

     */

    private WebDriver driver;
    private BasicAjaxPageObject basicAjaxPage;
    private ProcessedFormPageObject processedFormPage;

    @Before
    public void setUpTest() {
        driver = new ChromeDriver();
        basicAjaxPage = new BasicAjaxPageObject(driver);
        basicAjaxPage.get();
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample() {

        basicAjaxPage.selectCategory(Category.SERVER);
        //the above Category.SERVER is a static import. It's actually
        //BasicAjaxPageObject.Category.SERVER (check the imports at the top :-))

        basicAjaxPage.selectLanguage(Language.JAVA);
        //see comment above re: Language.JAVA

        basicAjaxPage.clickCodeInIt();

        processedFormPage = new ProcessedFormPageObject(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected Java code", "23", processedFormPage.getValueFor("language_id"));
    }

    @Test
    public void chooseToCodeInJavaScriptOnTheWeb() {

        basicAjaxPage.selectCategory(Category.WEB);

        basicAjaxPage.selectLanguage(Language.JAVASCRIPT);
        basicAjaxPage.clickCodeInIt();

        processedFormPage = new ProcessedFormPageObject(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected JavaScript code", "1", processedFormPage.getValueFor("language_id"));
    }

    @Test
    public void chooseToCodeInCppOnDesktop() {

        basicAjaxPage.selectCategory(Category.DESKTOP);

        basicAjaxPage.selectLanguage(Language.CPLUSPLUSDESKTOP);
        basicAjaxPage.clickCodeInIt();

        processedFormPage = new ProcessedFormPageObject(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected C++ code", "10", processedFormPage.getValueFor("language_id"));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    /*

    Here is a summary of what we have done in our refactoring:

    - Local methods become page objects
        - 2 pages - BasicAjaxPage and FormProcessedPage

    - Move synchronisation code on ajax into page
        - As an expected condition
        - And in the SelectCategory method

    - Created an enum for Category

    - Moved title synchronisation into page object

    - Tests now easy to expand

    */
}
