package com.richard.selenium.section_28_different_browsers;

import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

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
        //TODO so that i get the idea of what is possible with a profile. this test will fail at present.

        FirefoxProfile profile = new FirefoxProfile();

        //There are a few interesting methods that i can call from profile e.g. adding an extension to aid with debugging
        profile.setEnableNativeEvents(true);

        WebDriver driver = new FirefoxDriver(profile);

        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html/");

        assertThat(driver.getTitle(), is("HTML Form Elements"));

        driver.quit();
    }

    @Test
    public void firefoxDriverWithCapabilitiesForProxy() {

        //TODO note that this used to work with previous versions of firefox. but not at the moment - so just writing it
        //TODO so that i get the idea of what is possible with a proxy. this test will fail at present.

        //A proxy allows me to monitor my web traffic
        //Alan says that he normally uses fiddler for this but we will not use it in this instance
        //Sometimes we want to start up the proxy from the code (as below), monitor the traffic, dump it out to a file
        //and then stop - so that is what we are doing here...

        Proxy proxy = new Proxy();
        proxy.setHttpProxy("localhost:8080"); //this is the default proxy for burpsuite
        proxy.setFtpProxy("localhost:8080");
        proxy.setSslProxy("localhost:8080");

        //So before i run the test, i would want to start up burpsuite (Alan does this in the video).
        //Then i can run my test + monitor the traffic through burpsuite

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        WebDriver driver = new FirefoxDriver(capabilities);

        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html/");

        assertThat(driver.getTitle(), is("HTML Form Elements"));

        driver.quit();
    }

    @Test
    public void firefoxUseExtensions()
            throws IOException {

        //TODO note that this should work with previous versions of firefox. but not at the moment - so just writing it
        //TODO so that i get the idea of what is possible with adding extensions. this test will fail at present.

        //Profile is good for setting preferences and fiddling with browser settings...
        //e.g. i can run a test on a browser that already has the firebug extension installed. by default it's not!

        //first of all, i need to download the firebug extension to a local folder (TODO = i've not done this yet)
        String extensionPath = System.getProperty("user.dir") + "EXTENSION DIRECTORY";

        System.out.println(extensionPath);

        FirefoxProfile profile = new FirefoxProfile();

        //stop firebug showing the first run screen by setting the latest version to the current one
        profile.setPreference("extensions.firebug.currentVersion", "2.0.17");

        //add the extension to firefox
        profile.addExtension(new File(extensionPath));

        WebDriver driver = new FirefoxDriver(profile);
        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html/");

        assertThat(driver.getTitle(), is("HTML Form Elements"));

        driver.quit();
    }
}