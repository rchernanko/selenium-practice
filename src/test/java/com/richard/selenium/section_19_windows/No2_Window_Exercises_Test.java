package com.richard.selenium.section_19_windows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class No2_Window_Exercises_Test {

    /*

    Using http://www.compendiumdev.co.uk/selenium/frames/index.html...

    1) Amend the example (from No1_Windows_Introduction_Test) to switch back to the frame window and demonstrate that you
    switched control

    2) 2 of the links on the content frame can be switched to by name (eviltester.com and Compendium Developments).
    Open both of them and switch between all 3 windows (2 of them using switchTo("name"). Close each window when done
    with it

     */


    private WebDriver driver;

    @Before
    public void instantiateDriver() {
        driver = new ChromeDriver();
    }

    @Test
    public void exercise1SwitchByWindowHandle() {
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/frames/index.html");

        //we start with one window open
        assertEquals("Expected only 1 current window", 1, driver.getWindowHandles().size());

        //remember the current window handle
        String framesWindowHandle = driver.getWindowHandle();

        driver.switchTo().frame("content");

        //clicking on this will open a new window
        driver.findElement(By.cssSelector("a[href=\"http://www.seleniumsimplified.com\"]")).click();

        assertEquals("Expected a new window opened", 2, driver.getWindowHandles().size());

        Set<String> myWindows = driver.getWindowHandles();
        String newWindowHandle = "";

        //find the new window handle
        for(String aHandle : myWindows) {
            if(!framesWindowHandle.contentEquals(aHandle)) {
                newWindowHandle = aHandle;
                break;
            }
        }

        //switch to the new window
        driver.switchTo().window(newWindowHandle);

        //driver commands now act on new window
        assertTrue("Expected Selenium Simplified site", driver.getTitle().contains("Selenium Simplified"));

        driver.switchTo().window(framesWindowHandle);

        assertThat("Driver has not switched back to the initial window", driver.getTitle(), is("Frameset Example Title (Example 6)"));
    }

    @Test
    public void exercise2SwitchByWindowName() {

        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/frames/index.html");

        //we start with one window open
        assertEquals("Expected only 1 current window", 1, driver.getWindowHandles().size());

        //remember the initial windows's handle
        String initialWindowHandle = driver.getWindowHandle();

        driver.switchTo().frame("content");

        //clicking on this will open 2 new windows (at this stage, the driver is still in control of the initial window)
        driver.findElement(By.cssSelector("a[id=\"goevil\"]")).click();
        driver.findElement(By.cssSelector("a[href=\"http://www.compendiumdev.co.uk\"]")).click();

        /*

        MASSIVELY important point:

        a) with the first click (which opens eviltester website), the html looks like this:

        "
        <a id="goevil" href="" onclick="window.open('http://www.eviltester.com','evil')">EvilTester.com</a>
        "

        What is happening in the above? Well, there is an 'onclick' JavaScript event here - i.e. this window is being
        opened by JavaScript. In the "window.open('http://www.eviltester.com','evil')" part of the above, it is "evil"
        which is the name of the window that will be opened. So I can switch to the new window using the name "evil".

        b) Different to the above, with the second click (which opens compendiumdev website), there is no javascript
        involved. But the target is named (compdev). "When this window is opened, this is the name to give it..."

        "
        <a href="http://www.compendiumdev.co.uk" target="compdev">Compendium Developments</a>
        "

         */

        //So now I can switch (by name) to the compdev window...
        driver.switchTo().window("compdev");
        assertThat(driver.getTitle(), is("Software Testing Consultancy, Books and Online Training"));

        //And I can then switch (by name) to the evil window...
        driver.switchTo().window("evil");
        assertThat(driver.getTitle(), is("Home | EvilTester.com"));

        //And now I am going to switch back to the initial window (by window handle)...
        driver.switchTo().window(initialWindowHandle);
        assertThat(driver.getTitle(), is("Frameset Example Title (Example 6)"));

        //Now I will close the other windows and just check the number of window handles available...
        driver.switchTo().window("compdev").close();
        assertEquals("Expected only 1 current window", 2, driver.getWindowHandles().size());

        driver.switchTo().window("evil").close();
        assertEquals("Expected only 1 current window", 1, driver.getWindowHandles().size());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
