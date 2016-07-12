package com.richard.selenium.section_20_manage_windows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class No1_Introduction_Test {

    /*

    There are few things I can do to manage the current window...

    driver.manage.window()

        - .maximise

        Size resizes the browser window...

        - .getSize (Size uses Dimension object, e.g. width and height)
        - .setSize (Size uses Dimension object, e.g. width and height)

        Position moves the window around...

        - .getPosition (Position uses Point object)
        - .setPosition (Position uses Point object)

    An example below...

     */

    private WebDriver driver;

    @Before
    public void instantiateDriver() {
        driver = new ChromeDriver();
    }

    @Test
    public void manageWindow() {

        driver.get("http://www.compendiumdev.co.uk/selenium/frames");

        Window window = driver.manage().window();

        //Position moves the window around and uses a Point object...
        window.setPosition(new Point(10,30));

        Point pos = window.getPosition();

        //Position uses X and Y co-ordinates...
        assertEquals(10, pos.getX());
        assertEquals(30, pos.getY());

        //Size resizes the browser window and uses a Dimension object...
        window.setSize(new Dimension(400,400));

        Dimension windowSizes = window.getSize();

        //Size uses Dimensions e.g. width and height...
        assertEquals(400, windowSizes.getWidth());
        assertEquals(400, windowSizes.getHeight());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
