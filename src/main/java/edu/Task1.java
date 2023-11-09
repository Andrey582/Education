package edu;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task1 {

    public static String calculateTime(String[] dateTime) {

        String temp;
        Duration duration;

        int hours = 0;
        int minutes = 0;

        for (String time : dateTime) {

            temp = time.replace(", ", "T");
            String[] splitDateTime = temp.split(" - ");

            LocalDateTime start = LocalDateTime.parse(splitDateTime[0], DateTimeFormatter.ISO_DATE_TIME);
            LocalDateTime end = LocalDateTime.parse(splitDateTime[1], DateTimeFormatter.ISO_DATE_TIME);

            duration = Duration.between(start, end);
            hours += duration.toHoursPart();
            minutes += duration.toMinutesPart();

        }

        StringBuilder sb = new StringBuilder();

        sb.append(hours / dateTime.length).append("ч ");
        sb.append(minutes / dateTime.length).append("м");

        return sb.toString();
    }

    private Task1() {
    }
}
