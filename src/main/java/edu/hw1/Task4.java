package edu.hw1;

public class Task4 {
    public static String fixString(String input) {

        char[] inputString = input.toCharArray();
        char temp;
        for (int i = 0; i < inputString.length - 1; i += 2) {
            temp = inputString[i];
            inputString[i] = inputString[i + 1];
            inputString[i + 1] = temp;
        }
        return String.valueOf(inputString);
    }
}
