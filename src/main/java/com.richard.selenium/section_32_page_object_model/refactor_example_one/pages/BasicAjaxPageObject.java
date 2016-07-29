package com.richard.selenium.section_32_page_object_model.refactor_example_one.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicAjaxPageObject {

    private WebDriver driver;

    public BasicAjaxPageObject(WebDriver aDriver) {
        driver = aDriver;
    }

    public enum Category {

        /*

        LOVE THIS!!!

         */

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

        /*

        LOVE THIS AS WELL!

         */

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
