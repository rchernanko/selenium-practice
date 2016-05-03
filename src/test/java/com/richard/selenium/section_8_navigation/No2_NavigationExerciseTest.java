/*

Read this class second.

 */

package com.richard.selenium.section_8_navigation;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.*;

public class No2_NavigationExerciseTest {

    /*

    This exercise asks me to explore the following methods relating to navigation within the Selenium API:

    .navigate()
    .refresh()
    .to()
    .forward()
    .back()

    And when I navigate to different pages using the above methods, the exercise asks me to check the page titles (to
    prove that the navigation has worked).

     */

    private WebDriver driver;

    private final String PROTOCOL = "http";
    private final String DOMAIN = "www.compendiumdev.co.uk";
    private final String ROOT_URL = PROTOCOL + "://" + DOMAIN;

    @Before
    public void createInstanceOfWebdriver() {
        driver = new ChromeDriver();
    }

    @Test
    public void checkNavigateToMethod() {
        driver.navigate().to(ROOT_URL);
        //get() and navigate.to() result in the same thing - up to you what method you want to use
        Assert.assertTrue("Page title is incorrect",
                driver.getTitle().matches("Software Testing Essays, Book Reviews and Information"));
    }

    @Test
    public void checkRefreshMethod() throws MalformedURLException {
        URL seleniumPage = new URL(PROTOCOL, DOMAIN, "/selenium");
        //The above is a Java class that simply allows you to build a URL
        driver.navigate().to(seleniumPage);
        String initalPageTitle = driver.getTitle();
        driver.navigate().refresh();
        Assert.assertThat("Refreshing of page is unsucessful", driver.getTitle(), is(initalPageTitle));
    }

    @Test
    public void checkForwardBackMethods() {

        //Go to home page

        driver.get(ROOT_URL);
        //get() and navigate.to() result in the same thing - up to you what method you want to use
        Assert.assertThat("Page title is incorrect", driver.getTitle(), is("Software Testing Essays, Book Reviews and Information"));

        //Then go to search page

        driver.navigate().to(ROOT_URL + "/selenium/search.php");
        Assert.assertThat("Page title is incorrect", driver.getTitle(),
                startsWith("Selenium Simplified Search Engine"));

        //Then navigate back to the home page

        driver.navigate().back();
        Assert.assertThat("Page title is incorrect", driver.getTitle(), startsWith("Software Testing Essays"));

        //Then navigate forward to the search page

        driver.navigate().forward();
        Assert.assertThat("Page title is incorrect", driver.getTitle(), is("Selenium Simplified Search Engine"));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    /*

    Does navigation require synchronisation?

    When you do a driver.get() / driver.navigate.to(), webDriver will block until the HTML for the page is loaded.

    BUT, HTML being loaded does not necessarily mean that the page is ready for manipulation or interrogation.
    Execution of JavaScript may not have taken place at this point.

    Other forms of navigation do require synchronisation and we will cover these in latter tutorials
    (e.g. clicking on buttons, links, forms etc - typical user navigation)

    */
}
