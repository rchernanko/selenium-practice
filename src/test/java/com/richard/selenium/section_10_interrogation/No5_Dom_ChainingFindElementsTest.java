package com.richard.selenium.section_10_interrogation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class No5_Dom_ChainingFindElementsTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://www.compendiumdev.co.uk";

    @Before
    public void createDriverInstanceAndNavigateToPage() {
        driver = new ChromeDriver();
        driver.get(BASE_URL + "/selenium/find_by_playground.php");
    }

    //We have the ability to chain .findElement - as below:

    @Test
    public void findElementChainingExample() {
        WebElement element = driver.findElement(By.id("div1")).
                                    findElement(By.name("pName3")).
                                    findElement(By.tagName("a"));
        Assert.assertEquals("Expected a different id", "a3", element.getAttribute("id"));
    }

    //Note that you cannot chain .findElements() as it returns a list of WebElements

    //There are several support classes that I should really have a look at too. TODO
    //An example is ByChained (which extends By) - see an example of implementation below:

    @Test
    public void findElementByChainedSupportClassExample() {
        WebElement element = driver.findElement(new ByChained(
                                                        By.id("div1"),
                                                        By.name("pName3"),
                                                        By.tagName("a")));
        Assert.assertEquals("Expected a different id", "a3", element.getAttribute("id"));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
