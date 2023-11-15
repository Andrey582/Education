package edu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {

    public static boolean isOdd(String input) {
        Pattern pattern = Pattern.compile("^([0|1]{2})*[0|1]$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean isOddAndStartWithZeroOrEvenAndStartWithOne(String input) {
        Pattern pattern = Pattern.compile("^(0([0|1]{2})*)$|^(1([0|1]{2})*[0|1])$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean zeroCountMultipleOfThree(String input) {
        Pattern pattern = Pattern.compile("^((1*01*){3})+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean everyOddElementEqualsOne(String input) {
        Pattern pattern = Pattern.compile("^(1[01])*1?$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private Task8() {
    }
}
