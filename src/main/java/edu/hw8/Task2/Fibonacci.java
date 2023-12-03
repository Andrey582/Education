package edu.hw8.Task2;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
    private static List<Long> fibonacci;

    private static final int COUNT_NUMBERS_BY_ONE_THREAD = 5;

    public static List<Long> fibonacciNumbers(int countThreads) {

        fibonacci = new ArrayList<>();

        try (ThreadPool pool = FixedThreadPool.create(countThreads)) {

            pool.execute(() -> {
                for (int i = 0; i < COUNT_NUMBERS_BY_ONE_THREAD; i++) {
                    getNewElement();
                }
            });

            pool.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return List.copyOf(fibonacci);
    }

    private static synchronized void getNewElement() {
        if (fibonacci.isEmpty()) {
            fibonacci.add(0L);
        } else if (fibonacci.size() == 1) {
            fibonacci.add(1L);
        } else {
            fibonacci.add(fibonacci.get(fibonacci.size() - 1) + fibonacci.get(fibonacci.size() - 2));
        }
    }

    private Fibonacci() {
    }
}
