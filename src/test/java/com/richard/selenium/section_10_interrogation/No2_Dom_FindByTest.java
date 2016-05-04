/*

Interrogation.

He splits this into 2 types:

1) Interrogation of the driver (e.g. getCurrentUrl)

	- getTitle()
	- getCurrentUrl()
	- getPageSource() - be careful of using this one though, it may not return what you expect.

2) Interrogation of the DOM

    - In order to interrogate, you have to locate the DOM element (WebElement):
        - findElement()
        - findElements()

    - Once located, you can then interrogate using the WebElement Object methods:
        - getText()
        - getAttribute()
        - getTagName()
        - isEnabled()
        - isSelected()
        - isDisplayed()
        - getSize()
        - getLocation()
        - getCssValue()

 */

package com.richard.selenium.section_10_interrogation;

public class No2_Dom_FindByTest {

    /*

    Driver.findElement(By)

    We find an element by using a locator strategy e.g. by using the id, by evaluating an xpath expression, by
    executing a css selector etc

    Several methods within By class:

    - By.id
    - By.xpath
    - By.cssSelector
    - By.className
    - By.linkText
    - By.name
    - By.tagName
    - By.partialLinkText

     */

}
