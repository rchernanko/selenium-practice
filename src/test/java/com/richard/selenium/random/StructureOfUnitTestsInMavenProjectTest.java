package com.richard.selenium.random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StructureOfUnitTestsInMavenProjectTest {

    private StructureOfUnitTestsInMavenProject richard;

    @Before
    public void createInstanceOfClassToTest() {
        richard = new StructureOfUnitTestsInMavenProject();
    }

    @Test
    public void setValidNameTest() {
        richard.setName("jack");
        Assert.assertTrue("Name has not been set correctly", richard.getName().equals("jack"));
    }

    @Test
    public void setValidAgeTest() {
        richard.setAge(3);
        Assert.assertTrue("Age has not been set correctly", (richard.getAge() > 0));
    }



}

