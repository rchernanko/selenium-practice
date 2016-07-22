package com.richard.selenium.section_24_synchronisation_strategies_implicit_vs_explicit_waits;

public class No1_Introduction {

    /*

    Up until now, we've always been using explicit waits...

    Explicit Wait:

        - WebDriverWaits - with ExpectedConditions / ExpectedCondition
        - FluentWaits

        Up until now, we have always been in control of the wait + we have said, "at this point i am going to wait"

    Implicit Wait:

    WebDriver does supply an implicit wait.

    And if we set it at a global driver level...

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    ...then findElements or findElements will wait for that period of time. It will poll for up to 10 seconds (as per above)
    In the above, if an element can not be found then a findElement would take 10 seconds before throwing an Exception

    .findElement / .findElements have an implicit wait - but the default is 0.

    If you think about it, it's probably better to always use explicit waits - even purely from a maintainability perspective.
    If you only ever use implicit waits, and you have synchronisation issues, they are trickier to identify. Using
    explicit waits force us to identify the synchronisation risks and then handle them in our tests.
    Easier to fix...
    Also, when you are developing your tests, if you are using implicit waits, you'll get feedback slower than by using
    explicit waits - so it could take you longer to write tests!

    SO WHY WOULD YOU EVER WANT TO USE IMPLICIT WAITS???????

    - Implicit can make initial tests faster to write
        - You don't worry about synchronisation when writing tests

    - You don't have to think about synchronisation risks...but as Alan says - are you doing your job properly ha!

    BUT...

    - It can be harder to add synchronisation later
        - You have to identify a source of intermittency

    - If you start with implicit then you can expose synchronisation problems by gradually reducing the implicit wait time


     */
}
