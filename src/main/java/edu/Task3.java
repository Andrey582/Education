package edu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    private final static String PATTERN_DAYS_AGO = "^(\\d+) days? ago";

    private static Pattern pattern;
    private static Matcher matcher;

    @SuppressWarnings("MagicNumber")
    public static Optional<LocalDate> parseDate(String string) {

        try {
            return Optional.of(LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyy-M-d", Locale.ENGLISH)));
        } catch (DateTimeParseException e) {
        }

        Optional<LocalDate> optDate = Optional.of(LocalDate.MAX);

        try {
            optDate = Optional.of(LocalDate.parse(string, DateTimeFormatter.ofPattern("d/M/yy")));
        } catch (DateTimeParseException e) {
            try {
                optDate = Optional.of(LocalDate.parse(string, DateTimeFormatter.ofPattern("d/M/yyyy")));
            } catch (DateTimeParseException dtpe) {
            }
        }

        if (!optDate.get().isEqual(LocalDate.MAX)) {
            return optDate;
        }

        pattern = Pattern.compile(PATTERN_DAYS_AGO);
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

    private Task3() {
    }
}
