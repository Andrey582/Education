package edu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private static final int THIRTEEN = 13;

    public static String[] getAllFridayThirteen(int year) {

        LocalDate date = LocalDate.of(year, 1, 1);
        List<String> dates = new ArrayList<>();

        date = date.withDayOfMonth(THIRTEEN);

        while (date.getYear() == year) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                dates.add(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            date = date.plusMonths(1);
        }

        return dates.toArray(new String[0]);
    }

    public static Temporal getNextClosestFridayThirteen(Temporal date) {
        TemporalAdjuster getFridayTheThirteen = TemporalAdjusters.ofDateAdjuster(localDate -> {
            LocalDate temp = localDate.withDayOfMonth(THIRTEEN);
            while (temp.getDayOfWeek() != DayOfWeek.FRIDAY) {
                temp = temp.plusMonths(1);
            }
            return temp;
        });

        return date.with(getFridayTheThirteen);
    }

    private Task2() {
    }
}
