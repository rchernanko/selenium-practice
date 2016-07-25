package com.richard.selenium.section_27_javascript;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

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

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
