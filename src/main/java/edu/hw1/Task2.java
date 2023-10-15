package edu.hw1;

public class Task2 {

    private final static int TEN = 10;

    public static int countDigits(long number) {
        if (number == 0) {
            return 1;
        }

        long inputNumber = number;

        short count = 0;
        while (inputNumber != 0) {
            inputNumber /= TEN;
            count++;
        }
        return count;
    }

    private Task2() {
    }
}
