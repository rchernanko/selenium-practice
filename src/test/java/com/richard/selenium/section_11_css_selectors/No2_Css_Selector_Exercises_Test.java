package com.richard.selenium.section_11_css_selectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class No2_Css_Selector_Exercises_Test {

    private final String FIRST_THING = "rwger";
    private final String SECOND = "fwe";
    private final String THIRD = "sdfs";
    private final String FOUR = "asdasd";

    private WebDriver driver;
    private static final String BASE_URL = "http://www.compendiumdev.co.uk";

    @Before
    public void createInstanceOfWebdriver() {
        driver = new FirefoxDriver();
    }

    @Test
    public void cssSelectorGetAttributeExample1() {
        //TODO
    }

    @Test
    public void cssSelectorGetAttributeExample2() {
        //TODO

    }

    @Test
    public void cssSelectorGetAttributeExample3() {
        //TODO

    }

    @Test
    public void cssSelectorGetAttributeExample4() {
    //TODO
    }

    @After
    public void quitDriverInstance() {
        driver.quit();
    }

}
