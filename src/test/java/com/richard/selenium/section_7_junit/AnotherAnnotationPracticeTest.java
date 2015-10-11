/*

Read this class forth.

 */

package com.richard.selenium.section_7_junit;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class AnotherAnnotationPracticeTest {

    @Test
    public void isStadiumCapacityUnderFiftyThousand() {
        FootballClub tottenhamHotspur = new FootballClub("Tottenham Hotspur", "White Hart Lane");
        tottenhamHotspur.setStadiumCapacity(36000);
        Assert.assertTrue("The football club's stadium has a bigger capacity than 50,000", tottenhamHotspur.getStadiumCapacity() < 50000);
    }

    @Test
    public void isStadiumCapacityOverFiftyThousand() {
        FootballClub arsenal = new FootballClub("Arsenal", "Emirates");
        arsenal.setStadiumCapacity(60000);
        Assert.assertFalse("The football club's stadium has a smaller capacity than 50,000", arsenal.getStadiumCapacity() < 50000);
    }

    @Test
    public void areFootballClubsEqual() {
        FootballClub chelsea = new FootballClub("Chelsea", "Stamford Bridge");
        FootballClub manchesterUnited = new FootballClub("Manchester United", "Old Trafford");
        Assert.assertNotEquals("The two football clubs are the same", chelsea, manchesterUnited);
    }

    @Test
    public void useHamcrestToTestIsInstanceOf() {
        Assert.assertThat(new FootballClub("Manchester City", "City Of Manchester Stadium"), instanceOf(FootballClub.class));
    }

    /*

     Assert.assertNotSame("The two football clubs are the same", chelsea, manchesterUnited);
     assertNotSame = Asserts that two objects do not refer to the same object.

     */

    @Test
    public void isTopSixClubCorrect() {
        FootballClub westHamUnited = new FootballClub("West Ham United", "Upton Park");
        westHamUnited.setTopSixClub(false);
        Assert.assertThat("West Ham are not a top six club", westHamUnited.getTopSixClub(), is(not(true)));
    }
}

class FootballClub {

    private String name;
    private String stadiumName;
    private int stadiumCapacity;
    private boolean isTopSixClub;

    public FootballClub(String name, String stadiumName) {
        this.name = name;
        this.stadiumName = stadiumName;
    }

    public void setStadiumCapacity(int stadiumCapacity) {
        this.stadiumCapacity = stadiumCapacity;
    }

    public int getStadiumCapacity() {
        return stadiumCapacity;
    }

    public void setTopSixClub(boolean isTopSixClub) {
        this.isTopSixClub = isTopSixClub;
    }

    public boolean getTopSixClub() {
        return isTopSixClub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FootballClub that = (FootballClub) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
