package com.richard.selenium.section_35_ci_junit_suites.suites;

import com.richard.selenium.section_35_ci_junit_suites.test_classes.No2_Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//The below 2 annotations are standard Junit annotations - TODO - learn more about these
@RunWith(Suite.class) //this declares the runner that is going to be used
@Suite.SuiteClasses({ //this is an array - we will pass in the test classes we want to run as part of this suite
                      //so when i run this suite, it will run any tests marked with @Test in the class below
                      //i've also created another suite class "AnotherExampleSuiteTest"
        No2_Test.class
})
public class ExampleSuiteTest {
}
