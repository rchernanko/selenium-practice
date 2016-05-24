package com.richard.selenium.section_11_css_selectors;

public class No1_Introduction {

    /*

    Intro to CSS selectors:

    - A CSS selector is a pattern that matches a set of elements
    - Front end devs use CSS selectors to style HTML from CSS Stylesheets
    - TODO there are some useful links in the videos that I should go to in order to learn more about CSS and also XPATH
    - CSS selectors are reused in Selenium to match DOM elements

    - Generally much faster than looking for locators VS xpath
    - CSS selectors tend to be more flexible when locating elements on the page than e.g. ids, tag names, class names -
        Why?
        well if the elements move around the page as part of a restructure / refactor, the e.g. element location or id
        might no longer be valid - it may even no longer exist! In theory, the CSS selector is less likely to change +
        less likely to be impacted by any change in position of that element

    Basics of CSS selectors:

    * = match any element
    #id = match an id e.g. #p4
    .class = match a class e.g. .normal
    tag = match tags e.g. p
    [attribute] = match on the attribute name e.g. [id]
            e.g. *[id] will return any elements that contain / match the attribute id

    Note - I've installed the firebug extension on Firefox and have played around with searching for things with CSS selectors


    CSS Attribute matching:

    - Have a look at the readme - this gives some possible options for attribute matching
    TODO as above, play around with css selectors a little more to understand flexibility of attribute matching

     */
}
