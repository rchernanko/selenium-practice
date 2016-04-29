/*

Read this class forth.

 */

package com.richard.selenium.section_7_junit;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class No4_AnotherAnnotationPracticeTest {

    @Test
    public void isStadiumCapacityUnderFiftyThousand() {
        No4_FootballClub tottenhamHotspur = new No4_FootballClub("Tottenham Hotspur", "White Hart Lane");
        tottenhamHotspur.setStadiumCapacity(36000);
        Assert.assertTrue("The football club's stadium has a bigger capacity than 50,000",
                tottenhamHotspur.getStadiumCapacity() < 50000);
    }

    @Test
    public void isStadiumCapacityOverFiftyThousand() {
        No4_FootballClub arsenal = new No4_FootballClub("Arsenal", "Emirates");
        arsenal.setStadiumCapacity(60000);
        Assert.assertFalse("The football club's stadium has a smaller capacity than 50,000",
                arsenal.getStadiumCapacity() < 50000);
    }

    @Test
    public void areFootballClubsEqual() {
        No4_FootballClub chelsea = new No4_FootballClub("Chelsea", "Stamford Bridge");
        No4_FootballClub manchesterUnited = new No4_FootballClub("Manchester United", "Old Trafford");
        Assert.assertNotEquals("The two football clubs are the same", chelsea, manchesterUnited);
        //Checks that the values within the chelsea and manchesterUnited objects are different (see equals method within
        //the No4_FootballClub class. In this instance, it's simply comparing 'name'
        //This doesn't check whether the references point to different/same objects (see below - assertNotSame)
    }

    @Test
    public void useHamcrestToTestIsInstanceOf() {
        Assert.assertThat(new No4_FootballClub("Manchester City", "City Of Manchester Stadium"),
                instanceOf(No4_FootballClub.class));
    }
    //Very cool - instanceOf

    /*

     Assert.assertNotSame("The two football clubs are the same", chelsea, manchesterUnited);
     assertNotSame = Asserts that two references do not refer to the same object.

     */

    @Test
    public void isTopSixClubCorrect() {
        No4_FootballClub westHamUnited = new No4_FootballClub("West Ham United", "Upton Park");
        westHamUnited.setTopSixClub(false);
        Assert.assertThat("West Ham are not a top six club", westHamUnited.getTopSixClub(), is(not(true)));
    }
}