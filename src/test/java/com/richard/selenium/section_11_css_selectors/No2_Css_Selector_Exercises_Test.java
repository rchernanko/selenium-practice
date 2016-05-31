package com.richard.selenium.section_11_css_selectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class No2_Css_Selector_Exercises_Test {

    private final String EXAMPLE_1_CSS_SELECTOR = "#p31";
    private final String EXAMPLE_2_CSS_SELECTOR = "[name=\"ulName1\"]";
    private final String EXAMPLE_3_CSS_SELECTOR = ".specialDiv";
    private final String EXAMPLE_4_CSS_SELECTOR = "[name=\"liName1\"]";

    private WebDriver driver;
    private static final String BASE_URL = "http://www.compendiumdev.co.uk";

    @Before
    public void createInstanceOfWebDriverAndNavigateToPage() {
        driver = new FirefoxDriver();
        driver.navigate().to(BASE_URL + "/selenium/find_by_playground.php");
    }

    @Test
    public void cssSelectorGetAttributeExample1() {
        WebElement element = driver.findElement(By.id("p31"));
        Assert.assertEquals("The 2 elements are not equal", element.getAttribute("name"), "pName31");
    }

    //The below is the same as the above, but uses By.cssSelector instead
    @Test
    public void cssSelectorGetAttributeExample1a() {
        WebElement element = driver.findElement(By.cssSelector(EXAMPLE_1_CSS_SELECTOR));
        Assert.assertEquals("The 2 elements are not equal", element.getAttribute("name"), "pName31");
    }

    @Test
    public void cssSelectorGetAttributeExample2() {
        WebElement element = driver.findElement(By.name("ulName1"));
        Assert.assertEquals("The 2 elements are not equal", element.getAttribute("id"), "ul1");
    }

    //The below is the same as the above, but uses By.cssSelector instead
    @Test
    public void cssSelectorGetAttributeExample2a() {
        WebElement element = driver.findElement(By.cssSelector(EXAMPLE_2_CSS_SELECTOR));
        Assert.assertEquals("The 2 elements are not equal", element.getAttribute("id"), "ul1");
    }

    @Test
    public void cssSelectorGetAttributeExample3() {
        WebElement element = driver.findElement(By.className("specialDiv"));
        Assert.assertEquals("The 2 elements are not equal", element.getAttribute("id"), "div1");
    }

    //The below is the same as the above, but uses By.cssSelector instead
    @Test
    public void cssSelectorGetAttributeExample3a() {
        WebElement element = driver.findElement(By.cssSelector(EXAMPLE_3_CSS_SELECTOR));
        Assert.assertEquals("The 2 elements are not equal", element.getAttribute("id"), "div1");
    }

    @Test
    public void cssSelectorGetAttributeExample4() {
        WebElement element = driver.findElement(By.tagName("li"));
        Assert.assertEquals("The 2 elements are not equal", element.getAttribute("name"), "liName1");
    }

    //The below is the same as the above, but uses By.cssSelector instead
    @Test
    public void cssSelectorGetAttributeExample4a() {
        WebElement element = driver.findElement(By.cssSelector(EXAMPLE_4_CSS_SELECTOR));
        Assert.assertEquals("The 2 elements are not equal", element.getAttribute("name"), "liName1");
    }

    @After
    public void quitDriverInstance() {
        driver.quit();
    }

}
