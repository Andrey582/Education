package edu.hw1;

public class Task5 {
    private final static int TEN = 10;

    public static boolean isPalindromeDescendant(long number) {
        if (number < 0) {
            return false;
        }

        long inputNumber = number;

        int count = Task2.countDigits(number);

        int[] numberArray = new int[count];

        for (int i = count - 1; i >= 0; i--) {
            numberArray[i] = (int) (inputNumber % TEN);
            inputNumber /= TEN;
        }
        return isPalindromeArray(numberArray);
    }

    private static boolean isPalindromeArray(int[] numberArray) {
        if (numberArray.length < 2) {
            return false;
        }

        boolean isPalinsrome = true;
        for (int i = 0; i < numberArray.length / 2; i++) {
            if (numberArray[i] != numberArray[numberArray.length - 1 - i]) {
                isPalinsrome = false;
                break;
            }
        }

        if (isPalinsrome) {
            return true;
        } else {
            int newArraySize = 0;
            for (int i = 0; i < numberArray.length - 1; i += 2) {
                newArraySize++;
                if (numberArray[i] + numberArray[i + 1] >= TEN) {
                    newArraySize++;
                }
            }

            if (numberArray.length % 2 != 0) {
                newArraySize++;
            }

            int[] newNumberArray = new int[newArraySize];
            int newElement;

            for (int i = 0, j = 0; i < numberArray.length - 1; i += 2) {
                newElement = numberArray[i] + numberArray[i + 1];
                if (newElement >= TEN) {
                    newNumberArray[j] = newElement / TEN;
                    newNumberArray[j + 1] = newElement % TEN;
                    j++;
                } else {
                    newNumberArray[j] = newElement;
                }
                j++;
            }

            if (numberArray.length % 2 != 0) {
                newNumberArray[newArraySize - 1] = numberArray[numberArray.length - 1];
            }

            return isPalindromeArray(newNumberArray);
        }
    }

    private Task5() {
    }
}
