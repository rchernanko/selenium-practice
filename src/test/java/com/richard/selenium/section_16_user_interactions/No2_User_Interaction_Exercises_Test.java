package com.richard.selenium.section_16_user_interactions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class No2_User_Interaction_Exercises_Test {

    /*

    Time for some exercises.

    Using http://compendiumdev.co.uk/selenium/gui_user_interactions.html...

    1) Move draggable1 on to droppable1 and assert droppable1 text change to "Dropped!"
    2) Drag and drop draggable2 to droppable1 and assert droppable1 text change to "Get Off Me!"
    3) Press control+B and assert for text change on draggable1 to "Bwa! Ha! Ha!"

    Optional challenages:

    1) Can you draw something in the canvas?
    2) Control+Space and red squares say "Let GO!!" Can you assert for this?

     */

    private WebDriver driver;
    private static final String BASE_URL = "http://compendiumdev.co.uk/";
    private static final String DRAGGABLE_1_ELEMENT = "#draggable1";
    private static final String DRAGGABLE_2_ELEMENT = "#draggable2";
    private static final String DROPPABLE_1_ELEMENT = "#droppable1";
    private static final String DROPPABLE_1_ELEMENT_TEXT = "#droppable1>p";

    @Before
    public void instantiateDriverAndNavigateToPage() {
        driver = new ChromeDriver();
        driver.get(BASE_URL + "selenium/gui_user_interactions.html");
    }

    //1) Move draggable1 on to droppable1 and assert droppable1 text change to "Dropped!"
    @Test
    public void moveDraggable1ToDroppable1() {
        WebElement draggable1Element = driver.findElement(By.cssSelector(DRAGGABLE_1_ELEMENT));
        WebElement droppable1Element = driver.findElement(By.cssSelector(DROPPABLE_1_ELEMENT));
        new Actions(driver).clickAndHold(draggable1Element).moveToElement(droppable1Element).release().perform();
        Assert.assertEquals("Dropable1 text has not changed", "Dropped!", driver.findElement(By.cssSelector(DROPPABLE_1_ELEMENT_TEXT)).getText());
    }

    //2) Drag and drop draggable2 to droppable1 and assert droppable1 text change to "Get Off Me!"
    @Test
    public void dragAndDropDraggable2ToDroppable1() {
        WebElement draggable2Element = driver.findElement(By.cssSelector(DRAGGABLE_2_ELEMENT));
        WebElement droppable1Element = driver.findElement(By.cssSelector(DROPPABLE_1_ELEMENT));
        new Actions(driver).dragAndDrop(draggable2Element, droppable1Element).perform();
        Assert.assertEquals("Dropable1 text has not changed", "Get Off Me!", driver.findElement(By.cssSelector(DROPPABLE_1_ELEMENT_TEXT)).getText());
    }

    //3) Press control+B and assert for text change on draggable1 to "Bwa! Ha! Ha!"
    @Test
    public void pressControlBAndAssertText() {

    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
