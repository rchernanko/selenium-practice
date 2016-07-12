package com.richard.selenium.section_20_manage_windows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.firefox.MarionetteDriver;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class No2_Manage_Window_Exercise_Test {

    /*

    Using http://www.compendiumdev.co.uk/selenium/bounce.html...

    1) Maximise the window
    2) Reduce the window to half size.
    3) Move a half size window into the centre of the screen

     */

    private WebDriver driver;
    private Window window;

    @Before
    public void instantiateDriverAndGoToPage() {
        driver = new MarionetteDriver();
        window = driver.manage().window();
        driver.get("http://www.compendiumdev.co.uk/selenium/bounce.html");
    }

    @Test
    public void maximiseWindowTest() {

        //TODO - interestingly, the maximize didn't work with chrome browser...why? investigate further
        window.maximize();

        Dimension fullScreenSize = window.getSize();
        //TODO read about the java awt toolkit in a little more detail
        java.awt.Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

        String expected;

        expected = ((int) screenDimension.getWidth()) + " aprox (90% tolerance) " + fullScreenSize.getWidth();
        assertTrue(expected, (screenDimension.getWidth()*0.9) < fullScreenSize.getWidth());
        expected = ((int) screenDimension.getHeight()) + " aprox (90% tolerance) " + fullScreenSize.getHeight()*0.9;
        assertTrue(expected, (screenDimension.getHeight()*0.9) < fullScreenSize.getHeight());
    }

    @Test
    public void reduceWindowToHalfSize() {
        window.maximize();

        //Get full window size
        Dimension fullScreenSize = window.getSize();

        //Work out the reduced window size
        int reducedWidth = fullScreenSize.getWidth() / 2;
        int reducedHeight = fullScreenSize.getHeight() / 2;

        window.setSize(new Dimension(reducedWidth, reducedHeight));

        assertEquals("Window width has not be resized correctly", reducedWidth, window.getSize().getWidth());
        assertEquals("Window height has not be resized correctly", reducedHeight, window.getSize().getHeight());
    }

    //TODO run test below
    @Test
    public void moveHalfSizedWindowIntoCentreOfScreen() {
        window.maximize();

        //Get full window size
        Dimension fullScreenSize = window.getSize();

        window.setSize(new Dimension(fullScreenSize.getWidth() / 2, fullScreenSize.getHeight() / 2));

        //Then set the window to be in the middle of the screen

        //TODO not actually sure if this works...but will move on for now. To be investigated later on
        window.setPosition(new Point(fullScreenSize.getWidth() / 4, fullScreenSize.getHeight() / 4));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

}
