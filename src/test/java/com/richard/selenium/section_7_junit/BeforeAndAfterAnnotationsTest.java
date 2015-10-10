/*

Read this class first.

See notes in my evernote for further information :

If you had a class with e.g. 3 @Test methods, if you used a @Before tag, whatever is in the @Before method would run
before each @Test. Same with @After tag - the code witin the @After method would execute after each
individual @Test method.

@BeforeClass - runs before any @Before and @Test annotations. Runs once per class. Same with @AfterClass.

@BeforeClass > @Before > @Test > @After > @AfterClass

@BeforeClass and @AfterClass methods have to be declared as static + any fields they use need to be declared as
static too.

 */

package com.richard.selenium.section_7_junit;

import org.junit.*;

public class BeforeAndAfterAnnotationsTest {

    //The below will run once per class. Must be static
    //If I want to reference a variable in the class below (a static class), the variable must be static
    @BeforeClass
    public static void beforeClassMethod() {
        System.out.println("this i the before class method");
    }

    @Before
    public void beforeTestMethod() {
        System.out.println("this is the before test method");
    }

    @Test
    public void testMethod1() {
        System.out.println("this is test method 1");
    }

    @Test
    public void testMethod2() {
        System.out.println("this is test method 2");
    }

    @Test
    public void testMethod3() {
        System.out.println("this is test method 3");
    }

    @After
    public void afterTestMethod() {
        System.out.println("this is the after test method");
    }

    //What is very interesting to know is that when I run this class (BeforeAndAfterAnnotationsTest), the @Tests are not
    //run in any particular order - it can be random. Won't always be 1,2,3. Can sometimes be 3,1,2.

    //The below will run once per class. Must be static
    @AfterClass
    public static void afterClassMethod() {
        System.out.println("this is the after class method");
    }


}
