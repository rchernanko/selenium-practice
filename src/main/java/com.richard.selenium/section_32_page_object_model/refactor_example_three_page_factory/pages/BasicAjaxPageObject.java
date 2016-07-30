package com.richard.selenium.section_32_page_object_model.refactor_example_three_page_factory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicAjaxPageObject {

    private WebDriver driver;

    /*

    Instead of writing in a method...

    WebElement categorySelect = driver.findElement(By.id("combo1"));

    ...PageFactory allows me to do use the annotations below:

     */

    @FindBy (how = How.ID, using = "combo1")
    private WebElement categorySelect;

    @FindBy (how = How.ID, using = "combo2")
    private WebElement languageSelect;

    @FindBy (how = How.NAME, using = "submitbutton")
    private WebElement codeInIt;

    /*

    With PageFactory, we then need to initialise the above elements in the page constructor - this is where the magic
    happens! This initElements looks through the object and finds any fields that are annotated with @FindBy.

    */

    public BasicAjaxPageObject(WebDriver aDriver) {
        driver = aDriver;
        PageFactory.initElements(driver, this);
    }

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

    public void get() {
        driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    public void selectCategory(Category category) {

        //this is a little tidier now that I am using PageFactory + FindBy annotations at the top of the class
        categorySelect.findElement(By.cssSelector("option[value='" + category.value() +"']")).click();

        new WebDriverWait(driver, 10).until(ajaxActionIsComplete());
    }

    private ExpectedCondition<Boolean> ajaxActionIsComplete() {
        return ExpectedConditions.invisibilityOfElementLocated(
                By.id("ajaxBusy"));
    }

    public void selectLanguage(Language language) {
        //this is a little tidier now that I am using PageFactory + FindBy annotations at the top of the class
        languageSelect.findElement(By.cssSelector("option[value='" + language.value() +"']")).click();
    }

    public void clickCodeInIt() {
        //this is a little tidier now that I am using PageFactory + FindBy annotations at the top of the class
        codeInIt.click();
    }
}
