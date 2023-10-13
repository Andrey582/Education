package edu.hw1;

    public class Task1 {

    private static final int CONVERT_MINUTE_TO_SECONDS = 60;
    private static final int MAX_SECONDS_VALUE = 60;

    public static long minuteToSeconds(String time) {

        var arr = time.split(":");
        if (arr.length != 2) {
            return -1;
        }

        int minute = Integer.valueOf(arr[0]);
        int second = Integer.valueOf(arr[1]);

        if (second < 0 || second >= MAX_SECONDS_VALUE || minute < 0) {
            return -1;
        }

        return minute * CONVERT_MINUTE_TO_SECONDS + second;
    }

    private Task1() {
    }
}
