package com.richard.selenium.section_29_remote_web_driver;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class No1_Introduction {

    /*

    RemoteWebDriver is connects to a server somewhere, issues requests to that server, so that that server can then
    drive a local browser that's on that server. E.g. of a server = saucelabs.com

    Of course, when running locally, you don't really need a remote web driver...

    An example is as below:

    TODO - note that this will fail at the beforeclass part because i am not specifying any url... Hence not putting a
    Test at the end of this class name...

     */

    private static RemoteWebDriver aDriver;

    @BeforeClass
    public static void setupRemoteWebDriverForSauceLabs() {

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("version", "5");
        capabilities.setCapability("platform", Platform.XP);

        try {

            String sauceUrl = System.getenv("SAUCELABS_URL");

            // the url I put in here is the server URL that we want to connect too (e.g. saucelabs, selenium grid)
            aDriver = new RemoteWebDriver(new URL(sauceUrl), capabilities);

            aDriver.get("http://www.bbc.co.uk/football");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simpleTest() {

        aDriver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html/");

        assertThat(aDriver.getTitle(), is("HTML Form Elements"));

        aDriver.quit();
    }

    /*

    remember you can also set profiles / options with desired capabilities and then send them to the remotewebdriver
    (see image in readme)

     */

}
