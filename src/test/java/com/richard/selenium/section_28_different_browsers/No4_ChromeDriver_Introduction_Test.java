package com.richard.selenium.section_28_different_browsers;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class No4_ChromeDriver_Introduction_Test {

    /*

    I've put in some screenshots in the readme :-)

     */

    @BeforeClass
    public static void setUpTheChromeDriverSystemProperty() {

        //tell the webdriver where to find the chromedriver - i've actually done this in my pom file but is always good
        //to know how to do it here as well!

        String currentDir = System.getProperty("user.dir");
        String chromeDriverLocation = currentDir + "/tools/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

    }

    @Test
    public void basicChromeUsage(){

        WebDriver driver = new ChromeDriver();

        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");

        assertThat(driver.getTitle(), is("HTML Form Elements"));

        driver.quit();
    }

    @Test
    public void basicChromeDriverOptions() {

        //For more arguments, see http://peter.sh/experiments/chromium-command-line-switches/
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-extensions");

        WebDriver driver = new ChromeDriver(options);

        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");

        assertThat(driver.getTitle(), is("HTML Form Elements"));

        driver.quit();
    }

    //TODO get chromedriver.log working (see video)

    /*

    There was a proxy test as well but i didn't copy it - watch the video for more information

     */

}
