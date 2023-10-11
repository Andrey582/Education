package edu.hw1;

public class Task2 {
    public static int countDigits(long number) {
        if (number == 0) {
            return 1;
        }
        short count = 0;
        while (number != 0) {
            number /= 10;
            count++;
        }
        return count;
    }
}
