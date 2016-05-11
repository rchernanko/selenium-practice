package com.richard.selenium.section_10_interrogation;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class No4_Dom_FindByElementsExerciseTest {

    private static WebDriver driver;
    private static final String BASE_URL = "http://www.compendiumdev.co.uk";

    @BeforeClass
    public static void createInstanceAndNavigateToPage() {
        driver = new ChromeDriver();
        driver.navigate().to(BASE_URL + "/selenium/find_by_playground.php");
    }

    @Test
    public void find19DivElements() {
        List<WebElement> elements = driver.findElements(By.tagName("div"));
        Assert.assertThat("The number of div elements is incorrect", elements.size(), is(19));
    }

    @Test
    public void find25AElements() {
        List<WebElement> elements = driver.findElements(By.partialLinkText("jump to para"));
        Assert.assertEquals("The number of a elements is incorrect", 25, elements.size());
    }

    @Test
    public void find16NestedParagraphs() {
        List<WebElement> elements = driver.findElements(By.className("nestedDiv"));
        Assert.assertTrue("The number of nested paragraphs is incorrect", elements.size() == 16);
    }

    @Test
    public void find41ParagraphsInTotal() {
        List<WebElement> elements = driver.findElements(By.tagName("p"));
        Assert.assertEquals("The total mumber of paragraph elements is incorrect", 41, elements.size());
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}
