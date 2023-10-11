package edu.hw1;

public class Task3 {
    public static boolean isNestable(int[] firstArray, int[] secondArray) {
        if (secondArray == null || firstArray == null) {
            return false;
        }

        if (secondArray.length < 2 || firstArray.length < 1) {
            return false;
        }

        int maxFirst = firstArray[0];
        int minFirst = firstArray[0];
        int minSecond = secondArray[0];
        int maxSecond = secondArray[0];

        for (int val : firstArray) {
            if (maxFirst < val) {
                maxFirst = val;
            }
            if (minFirst > val) {
                minFirst = val;
            }
        }

        for (int val : secondArray) {
            if (maxSecond < val) {
                maxSecond = val;
            }
            if (minSecond > val) {
                minSecond = val;
            }
        }

        if (minFirst > minSecond && maxFirst < maxSecond) {
            return true;
        } else {
            return false;
        }
    }
}
