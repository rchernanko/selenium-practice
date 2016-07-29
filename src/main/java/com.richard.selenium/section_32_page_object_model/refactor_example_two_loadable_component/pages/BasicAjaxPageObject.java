package com.richard.selenium.section_32_page_object_model.refactor_example_two_loadable_component.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicAjaxPageObject extends LoadableComponent<BasicAjaxPageObject> {

    private WebDriver driver;

    public BasicAjaxPageObject(WebDriver aDriver) {
        driver = aDriver;
    }

    //I have had to implement this method because I have extended LoadableComponent
    @Override
    protected void load() {
        driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    //I have had to implement this method because I have extended LoadableComponent
    @Override
    protected void isLoaded() throws Error {
        try {
            driver.findElement(By.id("combo1"));
        } catch (NoSuchElementException e) {
            throw new Error("basic_ajax page not loaded");
        }
    }

    /*

    Note that I no longer require a get() method, I get this for free...
    So when I now call basicAjaxPage.get() in my test, the load and isLoaded methods are both called

    Of course, I now need to ask - do all my page objects need to extend LoadableComponent?

    If I look at the ProcessedFormPageObject, there is currently no get() method implemented - this is
    because I can get to that page by some other means (i.e. i never have to go directly to that page, it is
    loaded when i click on the submit button on the previous page.

    An isLoaded() on the ProcessedFormPageObject would be relevant, but not load()

    In the video Alan shows us a way how to get around this but is in 2 minds on whether he would want to do this in
    production. It's another interesting question relating to modelling again - watch the video again for further debate.

    So where we've left it, only one of our page objects extends LoadableComponent

     */

    public enum Category {

        WEB(1), DESKTOP(2), SERVER(3);

        private int dropDownValue;

        Category(int value) {
            this.dropDownValue = value;
        }

        public int value() {
            return dropDownValue;
        }
    }

    public enum Language {

        JAVA(23), JAVASCRIPT(1), CPLUSPLUSDESKTOP(10), VBSCRIPT(2), FLASH(3),
        ASSEMBLER(11), C(12), VISUALBASIC(13), COBOL(20), FORTRAN(21), CPLUSPLUSSERVER(22);

        private int dropDownValue;

        Language(int value) {
            this.dropDownValue = value;
        }

        public int value() {
            return dropDownValue;
        }
    }

    public void selectCategory(Category category) {

        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='" + category.value() +"']")).click();

        //We could also wait for the contents of the dropdown to fill (as below)

        /*new WebDriverWait(driver, 10).until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("option[value='23']")));*/

        //instead we will simply wait for the ajax symbol
        new WebDriverWait(driver, 10).until(ajaxActionIsComplete());
    }

    private ExpectedCondition<Boolean> ajaxActionIsComplete() {
        return ExpectedConditions.invisibilityOfElementLocated(
                By.id("ajaxBusy"));
    }

    public void selectLanguage(Language language) {
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='" + language.value() +"']")).click();
    }

    public void clickCodeInIt() {
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();
    }
}
