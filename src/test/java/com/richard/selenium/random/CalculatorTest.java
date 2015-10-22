package com.richard.selenium.random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void createInstanceOfCalculator() {
        calculator = new Calculator();
    }

    @Test
    public void validateAdditionOfTwoPositiveNumbers() {
        try {
            calculator.add(3, 2);
        } catch (Exception e) {
            System.out.println("This exception is thrown when the sum of the two numbers is more than 10");
        }
        Assert.assertTrue("Addition is not correct", calculator.getResult() == 5);
    }

    @Test (expected = Exception.class)
    public void validateExceptionIsThrownWhenResultIsLessThanZero() {
        try {
            calculator.add(-5, -10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (expected = Exception.class)
    public void validateExceptionIsThrownWhenResultIsMoreThanTen() {
        try {
            calculator.add(5, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateSubtraction() {
        calculator.subtract(3, 2);
        Assert.assertTrue("Subtraction is not correct", calculator.getResult() == 1);
    }

    @Test
    public void validateDivision() {
        calculator.divide(9, 3);
        Assert.assertTrue("Division is not correct", calculator.getResult() == 3);
    }

    @Test
    public void validateMultiplication() {
        calculator.multiply(3, 4);
        Assert.assertTrue("Multiplication is not correct", calculator.getResult() == 12);
    }

    //Read about some other unit tests i can do here - basic calculator example
}
