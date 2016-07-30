package com.richard.selenium.section_32_page_object_model.refactor_example_three_page_factory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcessedFormPageObject {

    /*

    I've not actually touched this page object

     */

    private WebDriver driver;

    public ProcessedFormPageObject(WebDriver aDriver) {
        driver = aDriver;
    }

    public void waitUntilPageIsLoaded() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));

    }

    public String getValueFor(String value_id) {
        WebElement fieldValueElement = driver.findElement(By.id("_value" + value_id));
        return fieldValueElement.getText();
    }
}
