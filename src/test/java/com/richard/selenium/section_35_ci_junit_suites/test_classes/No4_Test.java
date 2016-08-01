package com.richard.selenium.section_35_ci_junit_suites.test_classes;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

public class No4_Test {

    @Test
    public void printNo4Test() {
        System.out.println("I AM NO4 TEST");
    }

    // pretty cool - TODO should learn more about the parameters that you can put into junit annotations
    @Test(expected = NoSuchElementException.class)
    public void throwException() {
        throw new NoSuchElementException("thrown");
    }

    @Ignore
    public void ignoreThisTest() {
        System.out.println("THIS SHOULD BE IGNORED");
    }
}
