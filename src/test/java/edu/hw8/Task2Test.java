package edu.hw8;

import edu.hw8.Task2.Fibonacci;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @ParameterizedTest
    @MethodSource("fibonacciProvider")
    void fibonacciTest(int countThreads, List<Long> expected) {

        List<Long> result = Fibonacci.fibonacciNumbers(countThreads);

        assertThat(result).isEqualTo(expected);

    }

    public static Stream<Arguments> fibonacciProvider() {
        return Stream.of(
            Arguments.of(2, List.of(0L, 1L, 1L, 2L, 3L,
                5L, 8L, 13L, 21L, 34L)),
            Arguments.of(3, List.of(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L)),
            Arguments.of(5,
                List.of(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L,
                    377L, 610L, 987L, 1597L, 2584L, 4181L, 6765L, 10946L, 17711L, 28657L, 46368L))
        );
    }
}
