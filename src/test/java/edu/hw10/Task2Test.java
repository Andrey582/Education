package edu.hw10;

import edu.hw10.Task2.CacheProxy;
import edu.hw10.Task2.FibCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @Test
    void fibonacciTest() {

        FibCalculator cached = CacheProxy.create(fibCalculator, FibCalculator.class);

        long startFirst = System.nanoTime();
        long resultFirst = cached.fib(40);
        long endFirst = System.nanoTime();

        long startSecond = System.nanoTime();
        long resultSecond = cached.fib(40);
        long endSecond = System.nanoTime();

        long first = endFirst - startFirst;
        long second = endSecond - startSecond;

        assertThat(first)
            .isGreaterThan(second);
    }

    FibCalculator fibCalculator = new FibCalculator() {
        @Override
        public long fib(int number) {
            if (number == 0) {
                return 0;
            } else if (number == 1) {
                return 1;
            } else {
                return fib(number - 1) + fib(number - 2);
            }
        }
    };
}
