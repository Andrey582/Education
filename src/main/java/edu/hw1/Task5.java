package edu.hw1;

public class Task5 {
    private final static int TEN = 10;

    public static boolean isPalindromeDescendant(long number) {
        if (number < 0) {
            return false;
        }

        long inputNumber = number;

        int count = Task2.countDigits(number);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(inputNumber % TEN);
            inputNumber /= TEN;
        }
        sb.reverse();
        return isPalindromeStringBuilder(sb);
    }

    private static boolean isPalindromeStringBuilder(StringBuilder sb) {
        if (sb.length() < 2) {
            return false;
        }

        String reversed = sb.reverse().toString();
        sb.reverse();
        boolean isPalindrome = sb.toString().equals(reversed);

        if (isPalindrome) {
            return true;
        } else {
            StringBuilder newSB = new StringBuilder();
            int newElement;
            while (sb.length() > 1) {
                newElement = Character.getNumericValue(sb.charAt(0)) + Character.getNumericValue(sb.charAt(1));

                sb.deleteCharAt(0);
                sb.deleteCharAt(0);

                newSB.append(newElement);
            }

            if (sb.length() == 1) {
                newSB.append(sb.charAt(0));
            }

            return isPalindromeStringBuilder(newSB);
        }
    }

    private Task5() {
    }
}
