package com.richard.selenium.section_27_javascript;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class No4_Javascript_Async_Executor_Examples_And_AJAX_Exercise_Test {

    /*

    I simply watched the videos here - quite fiddly. Simply re-watch when required...

    //TODO learn more about javascript!

    INTERESTING - If you look at the javadocs for executeAsyncScript (Example 3) is interesting - basically this is how
    to do an AJAX request without having to interact with the front end GUI. THIS is the way we have done operations in
    the shopping-test-framework - post request :-)

    from javadocs in JavaScriptExecutor:

   * Example #3: Injecting a XMLHttpRequest and waiting for the result: <pre>{@code
   *   Object response = ((JavascriptExecutor) driver).executeAsyncScript(
   *       "var callback = arguments[arguments.length - 1];" +
   *       "var xhr = new XMLHttpRequest();" +
   *       "xhr.open('GET', '/resource/data.json', true);" +
   *       "xhr.onreadystatechange = function() {" +
   *       "  if (xhr.readyState == 4) {" +
   *       "    callback(xhr.responseText);" +
   *       "  }" +
   *       "};" +
   *       "xhr.send();");
   *   JsonObject json = new JsonParser().parse((String) response);
   *   assertEquals("cheese", json.get("food").getAsString());
   * }</pre>


    EXERCISE:

    Naughty me - I didn't actually do this but simply copied and pasted the answer (more from a time perspective as
    opposed to me not being arsed to do it...)

    - Look at the JavaDoc help for executeAsyncScript:

        - Create a test for the sleep sample code (in the javadocs)

        - Use the XMLHttpRequest sample to call /selenium/ajaxselect.php (this is the serve method which responds to the
          AJAX call on the application). You will need to add an id parameter to the call e.g. id=2

     */

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    @Test
    public void waitInBrowserForTimeSample() {

        //based on example 1 in the javadocs for javascriptexecutor

        //remember to set the script timeout when i am using executeAsyncScript!
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        long start = System.currentTimeMillis();

        //the javascript here is copied and pasted from within the javadoc example
        ((JavascriptExecutor) driver).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");

        System.out.println("ELapsed time: " + (System.currentTimeMillis() - start));

        //here, i am asserting that the browser 'went to sleep' for 500 milliseconds using JS
        assertTrue("Elapsed time should be greater than 500 milliseconds", System.currentTimeMillis() - start > 500);
    }

    @Test
    public void useXMLHttpRequest() {

        //based on example 3 in the javadocs for javascriptexecutor - i love this!!! this is what we used for operations
        //within the shopping-test-framework

        //remember to set the script timeout when i am using executeAsyncScript!
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        //this next bit is mainly copied and pasted from the example 3 + then tweaked for this particular test
        Object response = ((JavascriptExecutor) driver).executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "var xhr = new XMLHttpRequest();" +
                        "xhr.open('GET', '/selenium/ajaxselect.php?id=2', true);" +
                        "xhr.onreadystatechange = function() {" +
                        "  if (xhr.readyState == 4) {" +
                        "    callback(xhr.responseText);" +
                        "  }" +
                        "};" +
                        "xhr.send();");

        System.out.println((String) response);

        //check the response contains what i would expect it to
        assertThat((String) response, containsString("{optionValue:10, optionDisplay: 'C++'}"));

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
