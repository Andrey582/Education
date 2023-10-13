package edu.hw1;

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

        int bigger = Integer.valueOf(String.valueOf(sortDesc(charArrayNumber)));
        int lower = Integer.valueOf(String.valueOf(sortAsc(charArrayNumber)));

        int newNumber = bigger - lower;

        return newNumber == CONST_KAPREKARA ? 1 : 1 + kaprekar(newNumber);
    }

    private static char[] sortDesc(char[] array) {
        char max;
        char temp;
        int maxIndex;
        for (int i = 0; i < array.length - 1; i++) {
            max = array[i];
            maxIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (max < array[j]) {
                    max = array[j];
                    maxIndex = j;
                }
            }
            temp = array[i];
            array[i] = max;
            array[maxIndex] = temp;
        }
        return array;
    }

    private static char[] sortAsc(char[] array) {
        char min;
        char temp;
        int minIndex;
        for (int i = 0; i < array.length - 1; i++) {
            min = array[i];
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    minIndex = j;
                }
            }
            temp = array[i];
            array[i] = min;
            array[minIndex] = temp;
        }

        return array;
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
