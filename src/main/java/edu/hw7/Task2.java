package edu.hw7;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public static int factorial(int num) {
        if (num < 1) {
            throw new IllegalArgumentException();
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= num; i++) {
            list.add(i);
        }

        return list.parallelStream().reduce((fact, element) -> fact * element).get();
    }

    private Task2() {
    }
}
