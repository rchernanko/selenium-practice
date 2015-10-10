/*

Read this class second.

Exercise is as follows:

- create a test with - assertTrue, assertFalse, assertEquals - DONE
- Use @Before to assign a value to a field and use it in a test - DONE
- Use @BeforeClass to assign a value to a field and use it in a test - DONE
- Explore the documentation at junit.org - DONE, need to learn more over time though
- Explore the source for junit in your IDE - DONE
- How do you use assertThat? - DONE

 */


package com.richard.selenium.section_7_junit;

import org.junit.*;
import static org.hamcrest.CoreMatchers.is;

public class MyJunitExampleTest {

    private static String personOneName;
    private int personOneAge;
    private String personTwoName;

    public String getPersonOneName() {
        return personOneName;
    }

    public void setPersonOneAge(int personOneAge) {
        this.personOneAge = personOneAge;
    }

    public int getPersonOneAge() {
        return personOneAge;
    }

    public String getPersonTwoName() {
        return personTwoName;
    }

    public void setPersonTwoName(String personTwoName) {
        this.personTwoName = personTwoName;
    }

    @BeforeClass
    public static void setPersonOneName() {
        personOneName = "richard";
    }

    @Before
    public void setPersonOneAge() {
        setPersonOneAge(15);
    }


    @Test
    public void exampleAssertTrue() {
        Assert.assertTrue("The start of person one and person two's names is different", getPersonOneName().startsWith("ric"));
    }

    @Test
    public void exampleAssertFalse() {
        Assert.assertFalse("Person One's age should not be equal to the specified value", getPersonOneAge() == 13);
    }

    @Test
    public void exampleAssertEquals() {
        setPersonTwoName("richard");
        Assert.assertEquals("Person one and Person two's names are not equal", getPersonOneName(), getPersonTwoName());
    }

    @Test
    public void exampleAssertThat() {
        setPersonTwoName("richard");
        Assert.assertThat("The two names do not match", getPersonOneName(), is("richard"));
    }

}
