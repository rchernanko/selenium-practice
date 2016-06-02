package com.richard.selenium.section_14_manipulation_continued;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class No1_Manipulation_ExampleTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://compendiumdev.co.uk";

    @Before
    public void instantiateDriverAndVisitTestPage() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL + "/selenium/basic_html_form.html");
    }

    @Test
    public void simpleInteraction() {
        WebElement checkbox1 = driver.findElement(By.cssSelector("input[value=\"cb1\"]"));
        Assert.assertFalse("Checkbox is already selected and shouldn't be!", checkbox1.isSelected());

        //Let's click on the web element and then later assert that it's been selected
        checkbox1.click();

        Assert.assertTrue("The checkbox is not selected and should be!", checkbox1.isSelected());
    }

    @After
    public void quitDriverInstance() {
        driver.quit();
    }
}
