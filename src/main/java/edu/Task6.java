package edu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    public static boolean checkSubsequence(String find, String original) {
        Pattern pattern = Pattern.compile(find);
        Matcher matcher = pattern.matcher(original);
        return matcher.find();
    }
}
