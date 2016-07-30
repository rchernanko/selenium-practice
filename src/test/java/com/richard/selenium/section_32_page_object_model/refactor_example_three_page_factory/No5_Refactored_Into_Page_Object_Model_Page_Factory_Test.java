package com.richard.selenium.section_32_page_object_model.refactor_example_three_page_factory;

import com.richard.selenium.section_32_page_object_model.refactor_example_three_page_factory.pages.BasicAjaxPageObject;
import com.richard.selenium.section_32_page_object_model.refactor_example_three_page_factory.pages.ProcessedFormPageObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class No5_Refactored_Into_Page_Object_Model_Page_Factory_Test {

    /*

    This class has been refactored into a page object model, complete with some nice abstraction.
    To see the original class (which has driver.gets within the test etc), please look at
    "No2_TestClass_To_Be_Refactored".

    This particular class (compared to "No3_Refactored_Into_Page_Object_Model_Test" and
    "No4_Refactored_Into_Page_Object_Model_Loadable_Component_Test") uses PageFactory, another
    Selenium support class.

    Page Factory uses some nice @FindBy annotations that allows you to tidy your web elements up...
    It's basically a nice and tidy shortcut to finding web elements on a page

    Nothing in this test class changes, but go to the page objects for more information

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

        basicAjaxPage.selectCategory(BasicAjaxPageObject.Category.SERVER);
        //the above Category.SERVER is a static import. It's actually
        //BasicAjaxPageObject.Category.SERVER (check the imports at the top :-))

        basicAjaxPage.selectLanguage(BasicAjaxPageObject.Language.JAVA);
        //see comment above re: Language.JAVA

        basicAjaxPage.clickCodeInIt();

        processedFormPage = new ProcessedFormPageObject(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected Java code", "23", processedFormPage.getValueFor("language_id"));
    }

    @Test
    public void chooseToCodeInJavaScriptOnTheWeb() {

        basicAjaxPage.selectCategory(BasicAjaxPageObject.Category.WEB);

        basicAjaxPage.selectLanguage(BasicAjaxPageObject.Language.JAVASCRIPT);
        basicAjaxPage.clickCodeInIt();

        processedFormPage = new ProcessedFormPageObject(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected JavaScript code", "1", processedFormPage.getValueFor("language_id"));
    }

    @Test
    public void chooseToCodeInCppOnDesktop() {

        basicAjaxPage.selectCategory(BasicAjaxPageObject.Category.DESKTOP);

        basicAjaxPage.selectLanguage(BasicAjaxPageObject.Language.CPLUSPLUSDESKTOP);
        basicAjaxPage.clickCodeInIt();

        processedFormPage = new ProcessedFormPageObject(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected C++ code", "10", processedFormPage.getValueFor("language_id"));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
