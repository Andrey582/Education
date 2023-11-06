package edu;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    private final static String[] patternsDates = {
        "^\\d{1, 4}-\\d{1, 2}-\\d{1, 2}$",
        "^\\d{1, 2}/\\d{1, 2}/\\d{1, 4}"
    };

    private final static String patternFromNow = "^(\\d+) days? ago";

    public static Optional<LocalDate> parseDate(String string) {

        Pattern pattern;
        Matcher matcher;
        for (String patternString : patternsDates) {
            pattern = Pattern.compile(patternString);
            matcher = pattern.matcher(string);
            if (matcher.find()) {
                LocalDate date = LocalDate.parse(string);
                return Optional.of(date);
            }
        }

        pattern = Pattern.compile(patternFromNow);
        matcher = pattern.matcher(string);

        if (matcher.find()) {
            LocalDate date = LocalDate.now().minusDays(Long.parseLong(matcher.group(1)));
            return Optional.of(date);
        }

        return switch (string) {
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            case "today" -> Optional.of(LocalDate.now());
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            default -> Optional.empty();
        };

    }
}
