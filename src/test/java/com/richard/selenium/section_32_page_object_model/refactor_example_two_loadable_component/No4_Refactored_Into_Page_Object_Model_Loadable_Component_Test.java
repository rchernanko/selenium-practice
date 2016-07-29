package com.richard.selenium.section_32_page_object_model.refactor_example_two_loadable_component;

import com.richard.selenium.section_32_page_object_model.refactor_example_two_loadable_component.pages.BasicAjaxPageObject;
import com.richard.selenium.section_32_page_object_model.refactor_example_two_loadable_component.pages.ProcessedFormPageObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class No4_Refactored_Into_Page_Object_Model_Loadable_Component_Test {

    /*

    This class has been refactored into a page object model, complete with some nice abstraction.
    To see the original class (which has driver.gets within the test etc), please look at
    "No2_TestClass_To_Be_Refactored".

    This particular class (compared to "No3_Refactored_Into_Page_Object_Model_Test") uses LoadableComponent, a
    Selenium support class.

        - Extend to create page objects
            - Extends LoadableComponent<PageObject> e.g. LoadableComponent<BasicAjaxPageObject>

        - Then implement
            - Load (do the work to load the page)
            - IsLoaded (throw an error if component/page is not loaded)

        - Using LoadableComponent gives you get() for free...

        First thing to do is to extend the LoadableComponent in the page objects we are using. So have a look there!
        In fact, in this instance, there is absolutely no change to the test class itself (i.e. this class)



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
