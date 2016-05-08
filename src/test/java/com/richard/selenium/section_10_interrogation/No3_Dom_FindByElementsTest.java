/*

.findElement only returns 1 WebElement

When the "By" can return more than 1 element, .findElement only returns the FIRST in the list

.findElements returns the FULL list of matching WebElements

e.g. At 'http://www.compendiumdev.co.uk/selenium/find_by_playground.php'...

...driver.findElements(By.className("normal"));

The above returns ALL elements with the className "normal"

*/

package com.richard.selenium.section_10_interrogation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class No3_Dom_FindByElementsTest {

    //Below is an example from Alan:

    private WebDriver driver;
    private static final String BASE_URL = "http://www.compendiumdev.co.uk";

    @Before
    public void createDriverInstanceAndNavigateToPage(){
        driver = new FirefoxDriver();
        driver.get(BASE_URL + "/selenium/find_by_playground.php");
    }

    @Test
    public void returnAListOfElementsByClassNameTest() {

        List<WebElement> elements = driver.findElements(By.className("normal"));

        Set<String> foundTags = new HashSet<String>();
        //Sets store unique values only - HashSet stores in no particular order (see Udemy notes)

        for(WebElement webElement : elements) {
            foundTags.add(webElement.getTagName());
        }

        Assert.assertTrue("Expected a p tag", foundTags.contains("p"));
        Assert.assertTrue("Expected a ul tag", foundTags.contains("ul"));
        Assert.assertTrue("Expected a li tag", foundTags.contains("li"));
        Assert.assertTrue("Expected an a tag", foundTags.contains("as"));
        Assert.assertFalse("Did not expect a div tag", foundTags.contains("div"));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
