package com.richard.selenium.section_27_javascript;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class No1_Introduction {

    /*

    Alan says - he tends to use JavaScript a lot for workarounds - if there's something that he can't do with webdriver,
    he will look to use JavaScript...

    He also uses JS to create custom synchronisation - create expected conditions which are using JS

    He finds that using JS can sometimes make the application more testable - by amending the JS he can:

        - Adjust hidden fields
        - Amend values
        - etc

    He also finds that using JavaScript can help him to simulate hard to reach conditions

    JAVASCRIPT EXECUTOR

    The way that we use JS is by casting the driver to a JS executor. Most of the drivers will extend RemoteWebDriver -
    and this has the ability to act as a JS executor.

    And when the driver acts as a JS executor, we have 2 methods available to us:

        - .executeScript(script, args...)
        - .executeAsyncScript(script, args...) - execute a script asynchronously

    The basic difference between the 2 above:

        - .executeScript - executes a script and returns a value
        - .executeAsyncScript - will either timeout or stops executing that script when a callback function is called
            by the JS we are executing

            We will go into the above in more detail in latter tutorials

    The script that we are executing is a text string (which we can write).

    We can also pass arguments into the scripts - when we use arguments in the JS, we use them as an array called arguments

    Arguments are accessed using:

        - arguments[index] e.g. "document.title=arguments[0]"

    Return values are converted to Java types:

    When we return a value back, we have to call the JS expecting an object to come back, and then we cast that object
    into the type of value we are expecting to be returned.

        - So if we return a Html Element (from javascript), we will get a web element (in java)
        - if we return a decimal (from javascript), we will get a double (in java) etc

    The Javascript that we execute runs in an anonymous function - means that nothing is left behind when we run the
    javascript (unless we add variables / scripts into the global scope) - again, this will be demonstrated in latter
    videos. If you keep your variables / functions local, everything will be tidied up after your javascript execution.

    An example is below:

     */

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium/canvas_basic.html");
    }

    @Test
    public void callAJavaScriptFunctionOnThePage() {

        //Cast the driver to JavaScriptExecutor to access the JavaScript methods
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();

        assertEquals("By default app has 2 actions listed", 2, actionsCount);

        //Execute the 'draw' function on the page
        js.executeScript("draw(1, 150, 150, 40, '#FF1C0A');");

        actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();

        assertEquals("Calling draw should add an action", 3, actionsCount);
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
