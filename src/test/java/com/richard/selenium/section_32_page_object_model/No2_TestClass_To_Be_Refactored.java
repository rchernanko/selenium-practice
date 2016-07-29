package com.richard.selenium.section_32_page_object_model;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class No2_TestClass_To_Be_Refactored {

    /*

    Here is the test classes we are going to refactor into the page object model - I am not actually going to touch this
    class. Instead I will leave it as it is + create another class "No3" and do all of my page object model refactoring
    in there. This will therefore be a class I can refer back to to see what things USED TO BE LIKE.

     */

    private WebDriver driver;

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosNoSynchronisationExample(){

        try{
            driver = new ChromeDriver();

            driver.get("http://compendiumdev.co.uk/selenium/" +
                    "basic_ajax.html");

            // select Server
            WebElement categorySelect = driver.findElement(By.id("combo1"));
            categorySelect.findElement(By.cssSelector("option[value='3']")).click();

            // then select Java
            WebElement languageSelect = driver.findElement(By.id("combo2"));
            languageSelect.findElement(By.cssSelector("option[value='23']")).click();

            // Submit the form
            WebElement codeInIt = driver.findElement(By.name("submitbutton"));
            codeInIt.click();

            WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
            assertEquals("Expected Java code", "23",languageWeUsed.getText());

        }catch(Exception e){
            assertTrue("Expected some sort of exception thrown",true);
        }
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample(){

        startBrowserAndSelectServer();

        // wait until the ajax symbol has gone
        // because then the drop down has populated
        new WebDriverWait(driver,10).until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.id("ajaxBusy")));

        selectJavaSubmitFormAndCheckResult();
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionPresentExample(){

        startBrowserAndSelectServer();

        // wait until the option I want to click is present
        new WebDriverWait(driver,10).until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("option[value='23']")));

        // then select Java
        selectJavaSubmitFormAndCheckResult();
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionVisibleExample(){

        startBrowserAndSelectServer();

        // wait until the option I want to click is visible
        new WebDriverWait(driver,10).until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("option[value='23']")));

        // then select Java
        selectJavaSubmitFormAndCheckResult();
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionClickableExample(){

        startBrowserAndSelectServer();

        // wait until the option I want to click is clickable
        new WebDriverWait(driver,10).until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("option[value='23']")));

        // then select Java
        selectJavaSubmitFormAndCheckResult();
    }

    private void startBrowserAndSelectServer() {
        driver = new ChromeDriver();
        driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_ajax.html");

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();
    }

    private void selectJavaSubmitFormAndCheckResult() {
        // then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='23']")).click();

        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

}
