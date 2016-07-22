package com.richard.selenium.section_26_cookies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class No1_IntroductionTest {

    /*

    Inspect:

        - driver.manage
            - .getCookies()
            - .getCookieNamed("name")

    Interact:

        - driver.manage
            - .addCookie(Cookie)
            - .deleteAllCookies
            - .deleteCookie(Cookie)
            - .deleteCookieNamed("name")

    An example below...

    There is also a Cookie Builder which we will look at in the next few tutorials...

     */

    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("http://compendiumdev.co.uk/selenium/search.php");
    }

    @Test
    public void visitSearchPageAndCheckNoLastSearchCookie() {

        //delete all cookies for the current domain
        driver.manage().deleteAllCookies();

        //refresh the page to get an initial set of cookies
        driver.navigate().refresh();

        //find a cookie and return null if not found
        Cookie aCookie = driver.manage().getCookieNamed("SeleniumSimplifiedLastSearch");

        assertEquals("Should be no last search cookie", null, aCookie);
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
