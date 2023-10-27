package edu.hw3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Task2 {

    private final static String WRONG_SEQUENCE = "Wrong bracket sequence";

    public static List<String> clusterize(String input) throws IllegalArgumentException {
        Queue<Character> sequence = new ArrayDeque<>();
        List<String> output = new ArrayList<>();

        int countOpen = 0;
        for (char val : input.toCharArray()) {

            if (val == '(') {
                countOpen++;
            } else {

                if (countOpen == 0) {
                    throw new IllegalArgumentException(WRONG_SEQUENCE);
                }
                countOpen--;
            }

            sequence.add(val);

            if (countOpen == 0) {
                output.add(queueToString(sequence));
            }
        }

        if (countOpen != 0) {
            throw new IllegalArgumentException(WRONG_SEQUENCE);
        }

        return output;
    }

    private static String queueToString(Queue<Character> queue) {

        StringBuilder sb = new StringBuilder();
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            sb.append(queue.poll());
        }

        return sb.toString();
    }

    private Task2() {
    }
}
