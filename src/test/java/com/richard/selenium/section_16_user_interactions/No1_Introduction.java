package com.richard.selenium.section_16_user_interactions;

public class No1_Introduction {

    /*

    We've already seen that some of the Selenium / WebDriver commands that we use DON'T DO exactly the same thing as
    what happens in the real world...

    For example, "click"...

    "

    multiSelectOptions.get(0).click();
    multiSelectOptions.get(1).click();
    multiSelectOptions.get(2).click();

    It's really important here to understand that there is a slight nuance in web driver at this point (relating
    to the above)

    If (manually), a user firstly clicked on option 0, then on option 1 and then 2...ONLY option 2 would actually
    be end up being selected...

    However, web driver works differently and the above will actually ensure that options 0, 1 and 2 are ALL
    selected in the multi select area (it's like doing a CTRL + click)

    Is really important to be aware of this.

    Similarly, with the below. When you land on the form, before you've selected anything, the multiSelectOption 3
    is selected by default. Then, when you select another option (e.g. multiSelectOption 2), the default is cleared.
    However, when we use web driver, the default option is not cleared - hence why we have to un-select it below...

    "

    WHEN we encounter situations where the basic WebDriver commands don't fully cover the real world, that's where we
    turn to user interactions - these may more accurately simulate real-life behaviour better

    E.g. dragging and dropping things.

    BUT WARNING - user interactions are more complicated - use them as sparsely as possible if you want to make your
    tests robust. User interactions change between browser versions, driver versions etc.

    But still really important to learn about user interactions as sometimes they are absolutely necessary...

    HOW TO USE?

    We create a new Actions object (with a driver in its constructor):

    new Actions(driver)

    We then use build a sequence of actions and perform the action, for example:

    new Actions(driver).keyDown(Keys.CONTROL).sendKeys(Keys.SPACE).keyUp(Keys.CONTROL).perform();

    SOME ACTIONS SEQUENCES:

    .keyDown()
    .keyUp()
    .sendKeys()
    .clickAndHold()
    .release()
    .click()
    .doubleClick()
    .moveToElement()
    .moveByOffset()
    .contextClick()
    .dragAndDrop()
    .dragAndDropBy()
    .build() - you'd use this if you want to store and re-use the action sequence. Otherwise just use .perform()
    .perform()

     */

}
