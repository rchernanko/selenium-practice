package com.richard.selenium.section_17_alerts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class No2_Alerts_Exercises_Test {

    /*

    Using http://compendiumdev.co.uk/selenium/alerts.html...

    - For each alert, assert against getText()
    - Accept and dismiss 1st alert
    - Accept and dismiss 2nd confirm (check text on page changes)
    - Accept and dismiss 3rd prompt (check text on page changes)
    - Change text on prompt and accept and dismiss

     */

    private WebDriver driver;
    private static final String BASE_URL = "http://compendiumdev.co.uk/";
    private static final String ALERT_BUTTON = "#alertexamples";
    private static final String CONFIRM_BUTTON_TEXT = "#confirmreturn";
    private static final String CONFIRM_BUTTON = "#confirmexample";

    @Before
    public void instantiateDriverAndGoToPage() {
        driver = new ChromeDriver();
        driver.get(BASE_URL + "selenium/alerts.html");
    }

    @Test
    public void acceptAlertButtonAlert() {
        driver.findElement(By.cssSelector(ALERT_BUTTON)).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("The alert text is not correct", "I am an alert box!", alert.getText());
        alert.accept();
    }

    @Test
    public void dismissAlertButtonAlert() {
        driver.findElement(By.cssSelector(ALERT_BUTTON)).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("The alert text is not correct", "I am an alert box!", alert.getText());
        alert.dismiss();
    }

    @Test
    public void acceptConfirmButtonAlert() {
        String textBeforeAcceptingAlert = driver.findElement(By.cssSelector(CONFIRM_BUTTON_TEXT)).getText();
        driver.findElement(By.cssSelector(CONFIRM_BUTTON)).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("The alert text is not correct", "I am a confirm alert", alert.getText());
        alert.accept();
        String textAfterAcceptingAlert = driver.findElement(By.cssSelector(CONFIRM_BUTTON_TEXT)).getText();
        Assert.assertTrue("The text has not changed correctly",
                !textAfterAcceptingAlert.equals(textBeforeAcceptingAlert)
                        && textAfterAcceptingAlert.equals("true"));
    }

    @Test
    public void dismissConfirmButtonAlert() {
        String textBeforeAcceptingAlert = driver.findElement(By.cssSelector(CONFIRM_BUTTON_TEXT)).getText();
        driver.findElement(By.cssSelector(CONFIRM_BUTTON)).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("The alert text is not correct", "I am a confirm alert", alert.getText());
        alert.dismiss();
        String textAfterAcceptingAlert = driver.findElement(By.cssSelector(CONFIRM_BUTTON_TEXT)).getText();
        Assert.assertTrue("The text has not changed correctly",
                !textAfterAcceptingAlert.equals(textBeforeAcceptingAlert)
                        && textAfterAcceptingAlert.equals("false"));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
