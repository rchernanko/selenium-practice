package com.richard.selenium.section_28_different_browsers;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class No2_Firefox_Examples_Test {

    /*

    Firefox examples:

    We can control the preferences for firefox...

    How do we know the preferences? Well, in firefox, if you type in about:config in the search bar, you will see a
    list of preferences (see image in the readme).

    In terms of what they relate to, although a really outdated website - this is quite useful - this will tell you
    what the preferences actually relate to: http://preferential.mozdev.org/preferences.html

    We also have this - https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver

     */

    @Test
    public void firefoxDriverWithProfile() {

        //TODO note that this used to work with previous versions of firefox. but not at the moment - so just writing it
        //so that i get the idea of what is possible with a profile. this test will fail at present.

        FirefoxProfile profile = new FirefoxProfile();

        //There are a few interesting methods that i can call from profile e.g. adding an extension to aid with debugging
        profile.setEnableNativeEvents(true);

        WebDriver driver = new FirefoxDriver(profile);

        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html/");

        assertThat(driver.getTitle(), is("HTML Form Elements"));

        driver.quit();
    }
}
