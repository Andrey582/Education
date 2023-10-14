package edu.hw1;

import java.util.Arrays;

public class Task6 {

    private static final int CONST_KAPREKARA = 6174;
    private static final int NEEDED_NUMBER_COUNT = 4;

    public static int kaprekar(int number) {
        if (number == CONST_KAPREKARA) {
            return 0;
        }

        if (Task2.countDigits(number) != NEEDED_NUMBER_COUNT || number < 0) {
            return -1;
        }

        char[] charArrayNumber = String.valueOf(number).toCharArray();

        if (!haveUniqueDigit(charArrayNumber)) {
            return -1;
        }

        Arrays.sort(charArrayNumber);

        int lower = Integer.valueOf(String.valueOf(charArrayNumber));

        reverse(charArrayNumber);

        int bigger = Integer.valueOf(String.valueOf(charArrayNumber));

        int newNumber = bigger - lower;

        return newNumber == CONST_KAPREKARA ? 1 : 1 + kaprekar(newNumber);
    }

    private static void reverse(char[] array) {
        char temp;
        for (int i = 0; i < array.length / 2; i++) {
            temp = array[array.length - 1 - i];
            array[array.length - 1 - i] = array[i];
            array[i] = temp;
        }
    }

    private static boolean haveUniqueDigit(char[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] != array[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private Task6() {
    }
}
