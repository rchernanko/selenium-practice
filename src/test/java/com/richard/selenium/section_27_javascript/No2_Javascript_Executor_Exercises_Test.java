package com.richard.selenium.section_27_javascript;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class No2_Javascript_Executor_Exercises_Test {

    /*

    1) Call the draw function with arguments from the .executeScript method and then assert it drew e.g.:

            - .executeScript("document.title=arguments[0]", "bob");

    2) Execute a script which adds 2 arguments and assert the result:

            - "return 10;" will return 10

    3) Pass a WebElement as an argument, and use Jquery to 'hide' it. Then assert that it is hidden

            - e.g. to hide body using JQuery: $('body').hide();

    4) Create a test which adds JavaScript to the page - demonstrate that it persists beyond the anonymous function
       execution

     */

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium/canvas_basic.html");
    }

    @Before
    public void resetBeforeTest() {
        driver.navigate().refresh(); //set's the javascript back to the default before each test :-)
    }

    @Test
    public void exercise1PassArgumentsToTheJavaScript() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        int actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();
        assertEquals(2, actionsCount);

        //Now I am going to execute some JavaScript (within a for loop) with arguments that I am passing in
        for (int testLoop=0; testLoop < 10; testLoop++) {

            js.executeScript("draw(0, arguments[0], arguments[1], 20, arguments[2]);",
                    testLoop*20,
                    testLoop*20,
                    "#" + testLoop  + testLoop + "0000");
        }

        actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();
        assertEquals(12, actionsCount);
    }

    @Test
    public void exercise2ReturnValuesFromJavaScript() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        assertEquals("JavaScript should calculate correctly",
                40L,
                js.executeScript("return (arguments[0] + arguments[1]);",
                20, 20));
    }

    @Test
    public void returnHardCodedValueFromJavaScript() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        assertEquals("return 10", 10L, js.executeScript("return 10;"));
    }

    @Test
    public void changeTitleUsingJavaScript() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        assertEquals("Javascript Canvas Example", driver.getTitle());

        js.executeScript("document.title=arguments[0]", "richard");

        assertEquals("richard", driver.getTitle());
    }

    @Test
    public void useJQueryToHideBodyWithNoParams() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        assertTrue(driver.findElement(By.cssSelector("#commands")).isDisplayed());

        //Execute this jquery command - this will execute the hide function on the body
        js.executeScript("$('body').hide();");

        assertFalse(driver.findElement(By.cssSelector("#commands")).isDisplayed());
    }

    @Test
    public void hideWebElementAsParam() {

        //With this next one - instead of hiding the entire body (like in above), we are passing in a WebElement to hide

        JavascriptExecutor js = (JavascriptExecutor) driver;
        assertTrue(driver.findElement(By.cssSelector("#commands")).isDisplayed());

        //This hides a particular web element :-) pretty cool
        js.executeScript("$(arguments[0]).hide();", driver.findElement(By.cssSelector("#commands")));

        assertFalse(driver.findElement(By.cssSelector("#commands")).isDisplayed());
    }

    @Test
    public void javascriptRunsAsAnAnonymousFunctionButWeCanLeaveSomeBehind() {

        //In this exercise, Alan asked us - can we leave some JavaScript behind...?

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // this code runs as an anonymous function with no trace left
        js.executeScript("alert('alert triggered by webdriver')");

        assertThat(driver.switchTo().alert().getText(), is("alert triggered by webdriver"));
        driver.switchTo().alert().accept();

        // now let's achieve the same thing - an alert - but not run it within the anonymous function (as per above), but
        // instead add it to the global scope

        // here i am creating / adding a function into the window (part of global scope) - this is called
        // window.webdriveralert, and then i am calling it
        js.executeScript("window.webdriveralert = function(){alert('stored alert triggered by webdriver');};"+
                "window.webdriveralert.call();");

        //if i have successfully been able to add the above into the global window, the below line will pass. if not, it
        //will fail
        assertThat(driver.switchTo().alert().getText(), is("stored alert triggered by webdriver"));
        driver.switchTo().alert().accept();

        // this can only work if we managed to leave javascript lying around
        js.executeScript("window.webdriveralert.call();");
        assertThat(driver.switchTo().alert().getText(), is("stored alert triggered by webdriver"));
        driver.switchTo().alert().accept();

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
