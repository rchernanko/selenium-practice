package com.richard.selenium.section_35_ci_junit_suites.suites;

import com.richard.selenium.section_35_ci_junit_suites.test_classes.No3_Test;
import com.richard.selenium.section_35_ci_junit_suites.test_classes.No4_Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        No3_Test.class,
        No4_Test.class
})
public class AnotherExampleSuiteTest {
}
