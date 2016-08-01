package com.richard.selenium.section_35_ci_junit_suites;

public class No1_Introduction {

    /*

    We can run certain tests with a junit suite and then specify these as profiles within the pom.xml file

    For example I can run e.g. ONLY the chrome tests if a do a...

    mvn clean test -Pchrome

    To get things started, I have created an example of a basic Junit suite "ExampleSuiteTest" - go here now

    BACK AGAIN!

    So now I've configured some suites to run, I can now add them into my maven profile.

    So i've created 2 profiles in there - the key section is the "include" - here you can see the suites i will run
    with this profile...

    So if I do a mvn test -Prichardtest = the ExampleSuiteTest.class will be run

    And if I do a mvn test -Prichardanothertest = the AnotherExampleSuiteTest.class will be run

    Pretty simple really - TODO probably still need to figure out what's going on in the shopping-test-framework profiles


    OTHER:

    These videos also went through changes made to how we get the webdriver when we run the tests in CI - was quite good
    but I've not made any changes. This section, for me, is more about configuring the suites to run as part of maven
    profiles BUT note that there was also some other interesting information in the videos - would be good to watch again
    if i ever face issues with specific browsers...

     */
}
