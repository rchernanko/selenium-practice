/*

Interrogation.

He splits this into 2 types:

1) Interrogation of the driver (e.g. getCurrentUrl)

	- getTitle()
	- getCurrentUrl()
	- getPageSource() - be careful of using this one though, it may not return what you expect.

2) Interrogation of the DOM

    - In order to interrogate, you have to locate the DOM element (WebElement):
        - findElement()
        - findElements()

    - Once located, you can then interrogate using the WebElement Object methods:
        - getText()
        - getAttribute()
        - getTagName()
        - isEnabled()
        - isSelected()
        - isDisplayed()
        - getSize()
        - getLocation()
        - getCssValue()

 */

package com.richard.selenium.section_10_interrogation;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;

public class No2_Dom_FindByElementTest {

    /*

    Driver.findElement(By)

    We find an element by using a locator strategy e.g. by using the id, by evaluating an xpath expression, by
    executing a css selector etc

    Several methods within By class:

    - By.id
    - By.xpath
    - By.cssSelector
    - By.className
    - By.linkText
    - By.name
    - By.tagName
    - By.partialLinkText

    Let's write some tests for interrogation of the dom (point 2 in comments above)

    */

    /*

    Still need to do the below

        - findElement(By.xpath) TODO
        - isEnabled() TODO
        - getLocation() TODO
        - getCssValue() TODO

    */

    private static WebDriver driver;
    private static final String BASE_URL = "http://www.compendiumdev.co.uk";

    @BeforeClass
    public static void createDriverInstanceAndNavigateToPage() {
        driver = new ChromeDriver();
        driver.get(BASE_URL + "/selenium/find_by_playground.php");
    }

    @Test
    public void findElementByIdTest() {
        WebElement element = driver.findElement(By.id("a47"));
        Assert.assertThat("Element text is not correct", element.getText(), is("jump to para 21"));
    }

    /*

    @Test //TODO - But this will be covered in a later video so commenting out for now
    public void findElementByXpathTest() {
        Assert.assertTrue("Still need to do this", false);
    }

     */

    @Test
    public void findElementByCssSelectorTest() {
        WebElement element = driver.findElement(By.cssSelector(".nestedDiv #p35"));
        Assert.assertTrue("WebElement's attribute is not correct", element.getAttribute("name").equals("pName35"));
    }

    @Test
    public void findElementByClassNameTest() {
        WebElement element = driver.findElement(By.className("specialDiv"));
        Assert.assertEquals("Element text is not correct", element.getAttribute("name"), "mydivname");
    }

    @Test
    public void findElementByLinkTextTest() {
        WebElement element = driver.findElement(By.linkText("jump to para 23"));
        Assert.assertThat(element.getText(), is("jump to para 23"));
    }

    @Test
    public void findElementByNameTest() {
        WebElement element = driver.findElement(By.name("pName26"));
        Assert.assertTrue("WebElement's id is not correct", element.getAttribute("id").equals("p26"));
    }

    @Test
    public void findElementByTagNameTest() {
        WebElement element = driver.findElement(By.tagName("div"));
        //The above (By.tagName) will get the first element with the tag name "div"
        Assert.assertThat("", element.getAttribute("id"), is("div1"));
    }

    @Test
    public void findElementByPartialLinkTextTest() {
        WebElement element = driver.findElement(By.partialLinkText("para 12"));
        Assert.assertEquals(element.getTagName(), "a");
        //The above (getTagName) will get the tag of this element (which is 'a' - an href, a link)
    }

    @Test
    public void isElementDisplayedTest() {
        WebElement element = driver.findElement(By.cssSelector("#li16"));
        Assert.assertTrue("The WebElement is not displayed", element.isDisplayed());
    }

    /*

    Below are some tests that validate that a NoSuchElementException is thrown
    There are 2 ways to do this, either with a try/catch or with an expected next to the @Test annotation
    Alan prefers the try/catch because it is a bit more flexible

     */

    @Test
    public void tryCatchNoSuchElementExceptionTest() {

        try {
            WebElement element = driver.findElement(By.className("richard"));

            //If the above does not thrown a NoSuchElementException, then the line below will be executed and will fail
            //the test
            fail("Expected a NoSuchElementException to be thrown");

        } catch (NoSuchElementException e) {
            //Make sure I'm using the Selenium API NoSuchElementException and not the junit one! Be careful!!!
            System.out.println("NoSuchElementException is correctly thrown");
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void junitExpectedNoSuchElementExceptionTest() {
        WebElement element = driver.findElement(By.id("richard"));
    }

    @AfterClass
    public static void quitDriverInstance() {
        driver.quit();
    }
}