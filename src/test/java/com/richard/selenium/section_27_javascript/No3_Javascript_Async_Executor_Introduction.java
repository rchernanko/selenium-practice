package com.richard.selenium.section_27_javascript;

public class No3_Javascript_Async_Executor_Introduction {

    /*

    To recap, the javascript executor has 2 methods:

    1) executeScript
    2) executeAsyncScript

    Basic theory of the latter...

    When we call executeAsyncScript from webdriver, an additional argument is added into the script by webdriver, as a
    final argument:

    "var callback = arguments[arguments.length - 1];"

    The above says "get the last argument" - this is a callback function that we have to call from javascript to tell
    webdriver that our asynchronous execution has finished. If we do not do this, then executeAsyncScript will timeout
    and give a timeout exception.

    Any argument you pass to the callback function will be returned to WebDriver. So if you call the callback function
    with an argument, then that argument will be the return value that is sent to webdriver. Remember that HTML Element
    becomes WebElement etc. We call it expecting an Object and cast appropriately.

    Before we executeAsyncScript, we have to make sure we set the script timeout (SetScriptTimeout):

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

    Script timeout is set to 0 by default. So if we do not set the script timeout, our executeAsyncScript will immediately
    timeout and it won't work.

    Alan says he doesn't really use executeAsyncScript a lot - only in extreme situations...

     */
}
