package edu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    public static boolean checkSubsequence(String find, String original) {

        StringBuilder sb = new StringBuilder();

        for (var ch : find.toCharArray()) {
            sb.append(ch).append("+\\w*");
        }

        Pattern pattern = Pattern.compile(sb.toString());
        Matcher matcher = pattern.matcher(original);
        return matcher.find();
    }

    private Task6() {
    }
}
