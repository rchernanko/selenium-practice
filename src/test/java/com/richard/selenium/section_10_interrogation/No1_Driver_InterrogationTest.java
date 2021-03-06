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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.MarionetteDriver;

import static org.hamcrest.CoreMatchers.*;

public class No1_Driver_InterrogationTest {

    //Let's write some tests for interrogation of the driver (point 1 in comments above)

    private WebDriver driver;
    private static final String BASE_URL = "http://www.compendiumdev.co.uk/selenium/basic_web_page.html";

    @Before
    public void createDriverInstance() {
        driver = new MarionetteDriver();
    }

    @Test
    public void driverLevelInterrogation() {

        //Navigate to the page and assert the page title
        driver.get(BASE_URL);
        Assert.assertThat("Page title is incorrect", driver.getTitle(), is("Basic Web Page Title"));

        //assert current url
        Assert.assertTrue("Current url is incorrect", (driver.getCurrentUrl().equals(BASE_URL)));
        //Could've also done:
        //Assert.assertEquals("Current url is incorrect", driver.getCurrentUrl(), BASE_URL);

        //assert page source contains specific text
        Assert.assertThat("Page source does not contain specific text", driver.getPageSource(), containsString("A paragraph of text"));
        //Could've also done:
        //Assert.assertTrue(driver.getPageSource().contains("A paragraph of text"));

		/*

		Be careful with driver.getPageSource():

		The source code that is downloaded is not exactly the same as the file that is on the actual server.

		Different browsers will tailor the page source ever so slightly different.

		See diagram in the readme file.

		*/

    }

    @After
    public void quitDriver() {
        driver.quit();
    }}