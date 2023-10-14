package edu.hw1;

import java.util.Arrays;

public class Task3 {
    public static boolean isNestable(int[] firstArray, int[] secondArray) {
        if (secondArray == null || firstArray == null) {
            return false;
        }

        if (secondArray.length < 2 || firstArray.length < 1) {
            return false;
        }

        int maxFirst = Arrays.stream(firstArray).max().getAsInt();
        int minFirst = Arrays.stream(firstArray).min().getAsInt();
        int minSecond = Arrays.stream(secondArray).min().getAsInt();
        int maxSecond = Arrays.stream(secondArray).max().getAsInt();

        return minFirst > minSecond && maxFirst < maxSecond;
    }

    private Task3() {
    }
}
