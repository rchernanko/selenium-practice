package com.richard.selenium.section_25_synchronisation_best_practices;

public class No1_Finale {

    /*

    To make "waits" readable...

    - Wrap an Inline wait in a method e.g. ExpectedConditions - anonymousClassWrappedWithinAMethodExample() test
        in No4_CustomExpectedCondition_AnonymousClassWrappedInMethodExample_Test

    - Wrap an ExpectedCondition class in a method - see secondExerciseExampleTwo() test
        in section_22_synchronisation_strategies_expected_conditions.No5_Exercise_Test

    - Make sure that I use toString() when I create my own ExpectedCondition - this will help people later on

    - Create a 'wait' variable
        - Assign new WebDriverWait() in a before hook

    - Import ExpectedConditions statically - will make my tests more readble

    - Wait for a 'thing' then use it - IMPORTANT
        - What this means - if i am waiting for a WebElement, don't wait for it + then separately find it and return it
        - Instead, return it when i am waiting for it...
        - Kill 2 birds with 1 stone...

     */
}
