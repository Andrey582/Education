package edu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {

    public static boolean moreOrEqualsThreeAndZeroIsThird(String input) {
        Pattern pattern = Pattern.compile("^[0|1]{2}0+[0|1]*$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean startAndEndWithSameSymbol(String input) {
        Pattern pattern = Pattern.compile("^([0|1])[0|1]*\\1$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean lengthMoreOrEqualOneAndLessOrEqualThree(String input) {
        Pattern pattern = Pattern.compile("^[0|1]{1,3}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private Task7() {
    }
}
