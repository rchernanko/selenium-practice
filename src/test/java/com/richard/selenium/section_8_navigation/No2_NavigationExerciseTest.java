/*

Read this class second.

 */

package com.richard.selenium.section_8_navigation;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

    private final String BASE_URL = "http://compendiumdev.co.uk";

    @Before
    public void createInstanceOfWebdriver() {
        driver = new ChromeDriver();
    }

    @Test
    public void checkNavigateMethod() {
        driver.navigate().to(BASE_URL);
        Assert.assertTrue("Page title is incorrect",
                driver.getTitle().matches("Software Testing Essays, Book Reviews and Information"));
    }

    @Test
    public void checkRefreshMethod() {
        driver.get(BASE_URL + "/selenium");
        //get() and navigate.to() result in the same thing - up to you what method you want to use
        driver.navigate().refresh();
        Assert.assertThat("Refreshing of page is unsucessful", driver.getTitle(), is("Selenium Simplified - a book and ebook on Automated Web Testing with Java and Selenium RC"));
    }

    @Test
    public void checkForwardBackMethods() {

        //Go to home page

        driver.navigate().to(BASE_URL);
        Assert.assertThat("Page title is incorrect", driver.getTitle(), is("Selenium Simplified"));

        //Then go to search page

        driver.navigate().to(BASE_URL + "/selenium/search.php");
        Assert.assertThat("Page title is incorrect", driver.getTitle(),
                startsWith("Selenium Simplified Search Engine"));

        //Then navigate back to the home page

        driver.navigate().back();
        Assert.assertThat("Page title is incorrect", driver.getTitle(), startsWith("Selenium Simplified"));

        //Then navigate forward to the search page

        driver.navigate().forward();
        Assert.assertThat("Page title is incorrect", driver.getTitle(), is("Selenium Simplified Search Engine"));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    //Not passing at the moment. Up to video 64
}
