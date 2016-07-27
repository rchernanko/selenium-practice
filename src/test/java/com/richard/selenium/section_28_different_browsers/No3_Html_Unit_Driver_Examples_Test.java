package com.richard.selenium.section_28_different_browsers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class No3_Html_Unit_Driver_Examples_Test {

    /*

    Headless browser - more information here - http://htmlunit.sourceforge.net/

    PhantomJS is another headless browser.

    Some examples of HtmlUnitDriver are below - of course, nothing to see in the browser (no browser will open!), but
    good to know about this nonetheless

    People will say - if you want to do very quick basic html testing, you might as well use the html unit driver
    if you want to test your site without javascript, you can use the html unit driver

     */

    @Test
    public void basicHtmlUnitDriverBrowserVersion() {

        //this basically tells the htmlunitdriver to use headers that tells the application that i am coming from firefox 38
        WebDriver htmlUnit = new HtmlUnitDriver(BrowserVersion.FIREFOX_38);

        htmlUnit.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");

        assertThat(htmlUnit.getTitle(), is("HTML Form Elements"));

        htmlUnit.quit();
    }

    @Test
    public void basicHtmlUnitDriverJavaScriptOn() {

        //I am turning javascript on with this true - another way to achieve the same thing is using
        //capabilities (See test below)
        WebDriver htmlUnit = new HtmlUnitDriver(true);

        htmlUnit.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");

        assertThat(htmlUnit.getTitle(), is("HTML Form Elements"));

        htmlUnit.quit();
    }

    @Test
    public void basicHtmlUnitDriverCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setJavascriptEnabled(true);

        WebDriver htmlUnit = new HtmlUnitDriver(capabilities);

        htmlUnit.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");

        assertThat(htmlUnit.getTitle(), is("HTML Form Elements"));

        htmlUnit.quit();
    }
}