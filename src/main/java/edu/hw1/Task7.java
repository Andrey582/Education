package edu.hw1;

public class Task7 {
    public static int rotateRight(int number, int step) {
        if (number < 0) {
            return 0;
        }
        char[] charArrayNumber = Integer.toBinaryString(number).toCharArray();
        char temp;
        for (int i = 0; i < step; i++) {
            temp = charArrayNumber[charArrayNumber.length - 1];
            for (int j = charArrayNumber.length - 1; j > 0; j--) {
                charArrayNumber[j] = charArrayNumber[j - 1];
            }
            charArrayNumber[0] = temp;
        }
        return Integer.valueOf(String.valueOf(charArrayNumber), 2);
    }

    public static int rotateLeft(int number, int step) {
        if (number < 0) {
            return 0;
        }
        char[] charArrayNumber = Integer.toBinaryString(number).toCharArray();
        char temp;
        for (int i = 0; i < step; i++) {
            temp = charArrayNumber[0];
            for (int j = 0; j < charArrayNumber.length - 1; j++) {
                charArrayNumber[j] = charArrayNumber[j + 1];
            }
            charArrayNumber[charArrayNumber.length - 1] = temp;
        }
        return Integer.valueOf(String.valueOf(charArrayNumber), 2);
    }
}
