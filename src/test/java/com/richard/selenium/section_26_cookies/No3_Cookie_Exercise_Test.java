package com.richard.selenium.section_26_cookies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class No3_Cookie_Exercise_Test {

    /*

    Go to http://compendiumdev.co.uk/selenium/search.php

    Use the cookies:

        - seleniumSimplifiedSearchLastVisit
        - seleniumSimplfieidSearchNumVisits
        - seleniumSimplifiedLastSearch

    Write tests to:

        - Search for something and then check that the number of visits == 1. i.e. make the test think that it is coming
            to that page for the first time. This will need to be a repeatable test -

        - Set visits cookie to 42 and then check that the next visit is 43:

            - By cloning a cookie and amending it's values

            - And by creating a cookie from scratch (remember to experiment with Cookie.Builder and also just with
                the Cookie constructor)

    NOTE - Have a look at the 'things to be aware of with cookies' image in the readme for more info.

     */

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://compendiumdev.co.uk/selenium/search.php");
    }

    @Test
    public void checkNumberOfVisitsIs1() {

        //delete all the cookies
        driver.manage().deleteAllCookies();

        //do not refresh though - simply now do a search (and that will refresh the cookies when the page is reloaded)
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click(); //there's a default search term in the box

        assertThat("Cookie value is not correct", driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits").getValue(), is("1"));

    }

    @Test
    public void setVisitsCookieTo42AndThenCheckNextVisitIs43UsingCookieConstructor() {

        //Delete the cookie
        driver.manage().deleteCookie(driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits"));

        //Then before I refresh the page, create a new cookie (using the cookie constructor) with the amended value
        driver.manage().addCookie(new Cookie("seleniumSimplifiedSearchNumVisits", "42"));

        //Refresh the page
        driver.navigate().refresh();

        //Check that the cookie value is now 43
        assertThat("Cookie value is not correct", driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits").getValue(), is("43"));

    }

    @Test
    public void setVisitsCookieTo42AndThenCheckNextVisitIs43UsingCookieBuilder() {

        //Delete the cookie
        driver.manage().deleteCookie(driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits"));

        Cookie amendedNumberOfVisitsCookie = new Cookie.Builder("seleniumSimplifiedSearchNumVisits", "42").path("/selenium").build();

        //Then before I refresh the page, create a new cookie (using the cookie builder) with the amended value
        driver.manage().addCookie(new Cookie(amendedNumberOfVisitsCookie.getName(), amendedNumberOfVisitsCookie.getValue()));

        //Refresh the page
        driver.navigate().refresh();

        //Check that the cookie value is now 43
        assertThat("Cookie value is not correct", driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits").getValue(), is("43"));

    }

    @Test
    public void setVisitsCookieTo42AndThenCheckNextVisitIs43UsingClone() {

        //get the cookie
        Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");

        //clone the cookie but change the cookie value to 42
        Cookie aNewCookie = new Cookie(aCookie.getName(),
                String.valueOf(42),
                aCookie.getDomain(),
                aCookie.getPath(),
                aCookie.getExpiry(),
                aCookie.isSecure());

        driver.manage().deleteAllCookies();
        driver.manage().addCookie(aNewCookie);

        //Refresh the page
        driver.navigate().refresh();

        //because chromedriver creates 2 of the same cookie (a bug with chromedriver), i am having to have this workaround
        Set<Cookie> cookies = driver.manage().getCookies();

        for(Cookie cookie : cookies) {
            if (cookie.getName().contentEquals(aCookie.getName()) && cookie.getDomain().contentEquals(aCookie.getDomain())) {
                assertThat("Cookie value is not correct", cookie.getValue(), is("43"));
            }
        }
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
