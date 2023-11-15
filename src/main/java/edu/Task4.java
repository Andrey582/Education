package edu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {

    public static boolean checkPassword(String password) {
        Pattern pattern = Pattern.compile("\\w*[~!@#$%^&*|]+\\w*");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    private Task4() {
    }
}
