package com.richard.selenium.section_7_junit;

public class No4_FootballClub {

    private String name;
    private String stadiumName;
    private int stadiumCapacity;
    private boolean isTopSixClub;

    public No4_FootballClub(String name, String stadiumName) {
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        No4_FootballClub that = (No4_FootballClub) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}