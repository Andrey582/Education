package edu;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    private final static String PATTERN_WITH_HYPHEN = "^(\\d{4})-(\\d{1,2})-(\\d{1,2})$";
    private final static String PATTERN_WITH_SLASH = "^(\\d{1,2})/(\\d{1,2})/(\\d{2}){1,2}$";
    private final static String PATTERN_DAYS_AGO = "^(\\d+) days? ago";
    private final static int TWO_LAST_NUMBER_CURRENT_AGE = 23;
    private final static int YEAR_TWO_THOUSAND = 2000;
    private final static int YEAR_ONE_THOUSAND_NINE_HUNDRED = 1900;

    private static Pattern pattern;
    private static Matcher matcher;

    @SuppressWarnings("MagicNumber")
    public static Optional<LocalDate> parseDate(String string) {

        if (isMathc(string, PATTERN_WITH_HYPHEN)) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int days = Integer.parseInt(matcher.group(3));
            return Optional.of(LocalDate.of(year, month, days));
        }

        if (isMathc(string, PATTERN_WITH_SLASH)) {

            int year = Integer.parseInt(matcher.group(3));

            if (matcher.group(3).length() == 2) {
                year += year <= TWO_LAST_NUMBER_CURRENT_AGE ? YEAR_TWO_THOUSAND : YEAR_ONE_THOUSAND_NINE_HUNDRED;
            }

            int month = Integer.parseInt(matcher.group(2));
            int days = Integer.parseInt(matcher.group(1));

            return Optional.of(LocalDate.of(year, month, days));
        }

        if (isMathc(string, PATTERN_DAYS_AGO)) {
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

    private static boolean isMathc(String string, String stringPattern) {
        pattern = Pattern.compile(stringPattern);
        matcher = pattern.matcher(string);
        return matcher.find();
    }

    private Task3() {
    }
}
