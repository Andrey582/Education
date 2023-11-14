package edu.project3;

import edu.project3.Types.FormatType;
import edu.project3.Types.PathType;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {

    public static LocalDate parseDate(String date) {
        return date != null ? LocalDate.parse(date) : null;
    }

    public static PathType getPathType(String path) {
        Pattern pattern = Pattern.compile("^https?://");
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()) {
            return PathType.URL;
        } else {
            return PathType.FILE;
        }
    }

    public static FormatType getFormatType(String format) {
        return switch (format) {
            case "markdown" -> FormatType.MARKDOWN;
            case "adoc" -> FormatType.ADOC;
            case null, default -> null;
        };
    }
}
