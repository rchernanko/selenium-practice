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

public class No1_BeforeAndAfterAnnotationsTest {

    /*

    The below will run once per class. The BeforeClass method must be static

    If I want to reference a variable in the class below (a static class), the variable must be static

    Really weird, if i run this without a debug point, the beforeClass sout actually gets printed last.
    But then when you debug it, it actually runs first! Alan thinks it's just the IntelliJ console playing up
    And in fact, when you run this in the terminal, the beforeclass sout prints first :-)

    */

    @BeforeClass
    public static void beforeClassMethod() {
        System.out.println("@BeforeClass");
    }

    @Before
    public void beforeTestMethod() {
        System.out.println("@Before");
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
        System.out.println("@After");
    }

    /*

    What is very useful to know is that when I run this class (No1_BeforeAndAfterAnnotationsTest), the @Tests are not
    run in any particular order - it can be random. Won't always be 1,2,3. Can sometimes be 3,1,2.

    //The below @AfterClass method will run once per class and similar to the @BeforeClass, it must be static

    */

    @AfterClass
    public static void afterClassMethod() {
        System.out.println("@AfterClass");
    }


}
