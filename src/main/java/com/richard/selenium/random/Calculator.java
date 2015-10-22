package com.richard.selenium.random;

public class Calculator {

    private int result;

    public int getResult() {
        return result;
    }

    private void setResult(int result) {
        this.result = result;
    }

    public void add(int firstNumber, int secondNumber) throws Exception {
        if (firstNumber + secondNumber > 10) {
            throw new Exception("The result is more than 10");
        }
        if (firstNumber + secondNumber < 0) {
            throw new Exception("The result is less than 0");
        } else {
            setResult(firstNumber + secondNumber);
        }
    }

    public void subtract(int firstNumber, int secondNumber) {
        setResult(firstNumber - secondNumber);
    }

    //change above so that it throws an error if the result is more than ten or less than zero

    public void divide(int firstNumber, int secondNumber) {
        setResult(firstNumber / secondNumber);
    }

    //change above so that it throws an error if the number doesn't divide into a whole number e.g. 9/4

    public void multiply(int firstNumber, int secondNumber) {
        setResult(firstNumber * secondNumber);
    }

    //change above so that it throws an error if it is an even number
}
